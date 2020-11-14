package br.com.cassio.hotelaria.service;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.hotelaria.controller.exceptions.BusinessRuleBrokenException;
import br.com.cassio.hotelaria.controller.exceptions.EntityNotFoundException;
import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import br.com.cassio.hotelaria.model.CheckinCheckout;
import br.com.cassio.hotelaria.model.Pessoa;
import br.com.cassio.hotelaria.model.PessoaFisica;
import br.com.cassio.hotelaria.repository.CheckinCheckoutRepository;
import br.com.cassio.hotelaria.repository.PessoaRepository;

@Service
@Transactional
public class CheckinCheckoutService {

	private final LogHelper<CheckinCheckoutService> log = new LogHelper<>(CheckinCheckoutService.class);

	private final CheckinCheckoutRepository checkinCheckoutRepository;

	private final PessoaRepository pessoaRepository;
	private final PessoaFisicaService pessoaFisicaService;

	@Autowired
	public CheckinCheckoutService(
			CheckinCheckoutRepository checkinCheckoutRepository,
			PessoaFisicaService pessoaFisicaService,
			PessoaRepository pessoaRepository) {
		this.pessoaFisicaService = pessoaFisicaService;
		this.checkinCheckoutRepository = checkinCheckoutRepository;
		this.pessoaRepository = pessoaRepository;
	}

	public void deleteByCpfPessoa(String cpfPessoa) {
		final PessoaFisica pessoa = this.pessoaFisicaService.findByPessoaCpf(cpfPessoa);
		this.checkinCheckoutRepository.deleteByPessoaId(pessoa.getId());

	}

	public CheckinCheckout findByPessoaId(Long pessoaId) {
		final Optional<CheckinCheckout> optionalCheckinCheckout = this.checkinCheckoutRepository
				.findByPessoaIdAndPessoaExcluirFalse(pessoaId);

		final Supplier<? extends RuntimeException> entityNotFoundExceptionSupplier = this
				.pessoaNotFoundExceptionSupplier(
						pessoaId);

		final CheckinCheckout checkinCheckout = optionalCheckinCheckout.orElseThrow(entityNotFoundExceptionSupplier);

		return checkinCheckout;
	}

	private Supplier<? extends RuntimeException> pessoaNotFoundExceptionSupplier(Long pessoaId) {
		return () -> {
			final String simpleErrorMessage = "Pessoa não foi encontrada";
			throw new EntityNotFoundException(CheckinCheckout.class, "pessoa.id", pessoaId,
					simpleErrorMessage);
		};
	}

	public CheckinCheckout save(CheckinCheckout checkinCheckout) {
		this.log.info(LogMessage.SAVING, checkinCheckout);
		this.throwErrorIfPersonNotExists(checkinCheckout);
		this.throwErrorIfPessoaHasAnyCheckinCheckout(checkinCheckout.getPessoa().getId());

		final CheckinCheckout savedCheckinCheckout = this.checkinCheckoutRepository.save(checkinCheckout);
		return savedCheckinCheckout;
	}

	private void throwErrorIfPessoaHasAnyCheckinCheckout(Long pessoaId) {
		final Pessoa pessoa = this.pessoaRepository.findById(pessoaId)
				.orElseThrow(this.pessoaNotFoundExceptionSupplier(pessoaId));

		final Optional<CheckinCheckout> optionalCheckinCheckout = Optional.ofNullable(pessoa.getCheckinCheckout());
		if (optionalCheckinCheckout.isPresent()) {
			final String brokenRuleExceptionMessage = "Pessoa já possui checkin checkout cadastrado, remova-o antes de cadastrar mais um";
			throw new BusinessRuleBrokenException(CheckinCheckout.class, "pessoa.id",
					brokenRuleExceptionMessage);
		}

	}

	private void throwErrorIfPersonNotExists(CheckinCheckout checkinCheckout) {

		final Long idPessoa = checkinCheckout.getPessoa().getId();
		final Boolean pessoaNotExists = !this.pessoaRepository.existsById(idPessoa);

		if (pessoaNotExists) {
			final String exceptionMessage = String.format("Pessoa de id %d não existe", idPessoa);
			final String simpleErrorMessage = "Pessoa não encontrada";
			throw new EntityNotFoundException(Pessoa.class, "id", exceptionMessage, simpleErrorMessage);
		}
	}

	/**
	 * private boolean hasPessoaAnyActiveEndereco(final Pessoa pessoa) { final
	 * Predicate<? super Endereco> isEnderecoActive = endereco ->
	 * Boolean.FALSE.equals(endereco.getExcluir());
	 *
	 * final Stream<Endereco> pessoas = pessoa.getEnderecos().stream()
	 * .filter(isEnderecoActive); return pessoas.count() != 0; }
	 */
}
