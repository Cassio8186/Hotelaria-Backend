package br.com.cassio.hotelaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cassio.hotelaria.controller.exceptions.BusinessRuleBrokenException;
import br.com.cassio.hotelaria.controller.exceptions.EntityNotFoundException;
import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import br.com.cassio.hotelaria.model.Pessoa;
import br.com.cassio.hotelaria.model.PessoaFisica;
import br.com.cassio.hotelaria.repository.PessoaFisicaRepository;

@Service
@Transactional
public class PessoaFisicaService {

	private static final String PESSOA_NAO_ENCONTRADA_MESSAGE = "Pessoa não encontrada";

	private static String getPessoaNaoEncontradaSimpleMessage(String cpf) {
		final String mensagem = String.format("Pessoa de cpf %s não encontrada", cpf);
		return mensagem;
	}

	private final LogHelper<PessoaFisicaService> log = new LogHelper<>(PessoaFisicaService.class);

	private final PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository) {
		this.pessoaFisicaRepository = pessoaFisicaRepository;

	}

	public void delete(Long idPessoaFisica) {
		final String searchParameter = "id";

		final Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository
				.findByIdAndExcluirFalse(idPessoaFisica);
		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(PessoaFisica.class, searchParameter, idPessoaFisica,
					PESSOA_NAO_ENCONTRADA_MESSAGE);
		}
		final PessoaFisica pessoaFisica = optionalPessoaFisica.get();
		pessoaFisica.setExcluir(true);

		this.pessoaFisicaRepository.save(pessoaFisica);

	}

	public PessoaFisica findByPessoaCpf(String cpf) {
		final String searchParameter = "pessoa.cpf";

		final Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository.findByCpfAndExcluirFalse(cpf);

		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(Pessoa.class, searchParameter, cpf,
					getPessoaNaoEncontradaSimpleMessage(cpf));
		}
		final PessoaFisica pessoaFisica = optionalPessoaFisica.get();

		this.log.info(LogMessage.FOUND, PessoaFisica.class, pessoaFisica);
		return pessoaFisica;
	}

	public PessoaFisica save(PessoaFisica pessoaFisica) {
		final Boolean pessoaExists = this.pessoaFisicaRepository.existsByCpfAndExcluirFalse(pessoaFisica.getCpf());

		if (Boolean.TRUE.equals(pessoaExists)) {
			final String exceptionMessage = String.format(
					"Não é possível efetuar cadastro pois CPF %s já existe no banco de dados.", pessoaFisica.getCpf());
			throw new BusinessRuleBrokenException(Pessoa.class, "cpf", exceptionMessage);
		}
		this.log.info(LogMessage.SAVING, pessoaFisica);

		pessoaFisica.setExcluir(false);

		final PessoaFisica pessoa = this.pessoaFisicaRepository.save(pessoaFisica);
		return pessoa;
	}

	public PessoaFisica update(PessoaFisica pessoaFisica) {
		final Optional<PessoaFisica> optionalPessoaFisica = this.pessoaFisicaRepository
				.findByIdAndExcluirFalse(pessoaFisica.getId());

		if (!optionalPessoaFisica.isPresent()) {
			throw new EntityNotFoundException(PessoaFisica.class, "id", pessoaFisica.getId(),
					PESSOA_NAO_ENCONTRADA_MESSAGE);
		}
		this.log.info(LogMessage.UPDATING, pessoaFisica);

		final PessoaFisica pessoa = this.pessoaFisicaRepository.save(pessoaFisica);
		return pessoa;
	}

	public List<PessoaFisica> findAllPessoasFisicas() {
		this.log.info(LogMessage.FIND_ALL_BY_REQUEST, "excluir", "false");
		final List<PessoaFisica> pessoas = this.pessoaFisicaRepository.findAllByExcluirFalse();
		this.log.info(LogMessage.FIND_ALL_BY_SUCESS, "excluir", "false");
		return pessoas;
	}
}
