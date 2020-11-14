package br.com.cassio.hotelaria.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import br.com.cassio.hotelaria.model.CheckinCheckout;
import br.com.cassio.hotelaria.model.dto.CheckinCheckoutDTO;
import br.com.cassio.hotelaria.model.dto.CheckinCheckoutSaveDTO;
import br.com.cassio.hotelaria.model.mapper.CheckinCheckoutMapper;
import br.com.cassio.hotelaria.model.mapper.CheckinCheckoutSaveMapper;
import br.com.cassio.hotelaria.service.CheckinCheckoutService;
import br.com.cassio.hotelaria.service.PessoaFisicaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/v1/checkin-checkout")
public class CheckinCheckoutController {
	private final LogHelper<CheckinCheckoutController> log = new LogHelper<>(CheckinCheckoutController.class);
	private final CheckinCheckoutService checkinCheckoutService;

	public CheckinCheckoutController(
			CheckinCheckoutService checkinCheckoutService,
			PessoaFisicaService pessoaFisicaService) {
		this.checkinCheckoutService = checkinCheckoutService;
	}

	@GetMapping(value = "/{id-pessoaFisica}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna o checkin checkout de uma pessoa física")
	public ResponseEntity<CheckinCheckoutDTO> getCheckinCheckoutByPessoaId(

			@PathVariable("id-pessoaFisica") Long pessoaId) {
		final String searchParameter = "pessoa.id";
		try {

			this.log.info(LogMessage.FIND_BY_REQUEST, searchParameter, pessoaId);
			final CheckinCheckout checkinCheckout = this.checkinCheckoutService.findByPessoaId(pessoaId);
			this.log.info(LogMessage.FIND_BY_SUCCESS, searchParameter, pessoaId);

			final CheckinCheckoutDTO checkinCheckoutDTO = CheckinCheckoutMapper.INSTANCE.toDto(checkinCheckout);

			return ResponseEntity.ok(checkinCheckoutDTO);

		} catch (final Exception e) {
			this.log.info(LogMessage.FIND_BY_FAIL, searchParameter, pessoaId);
			throw e;
		}

	}

	@DeleteMapping(value = "/{cpf-pessoa}")
	@ApiOperation("Deleta uma checkin checkout pelo cpf da pessoa")
	public ResponseEntity<Void> deleteCheckinCheckoutById(@PathVariable("cpf-pessoa") String cpfPessoa) {
		final String searchParameter = "pessoaFisica.cpf";

		this.log.info(LogMessage.DELETE_REQUEST, searchParameter, cpfPessoa);
		try {

			this.checkinCheckoutService.deleteByCpfPessoa(cpfPessoa);

			this.log.info(LogMessage.DELETE_SUCCESS, searchParameter, cpfPessoa);
			return ResponseEntity.noContent().build();
		} catch (final Exception e) {
			this.log.error(LogMessage.DELETE_FAIL, searchParameter, cpfPessoa);
			throw e;
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Salva um checkin a uma pessoa física")
	public ResponseEntity<CheckinCheckoutDTO> saveCheckinCheckout(
			@RequestBody @Validated CheckinCheckoutSaveDTO checkinCheckoutSaveDTO) {
		this.log.info(LogMessage.SAVE_REQUEST, checkinCheckoutSaveDTO);
		try {
			final CheckinCheckout checkinCheckout = CheckinCheckoutSaveMapper.INSTANCE.toEntity(checkinCheckoutSaveDTO);

			final CheckinCheckout savedCheckinCheckout = this.checkinCheckoutService.save(checkinCheckout);

			final CheckinCheckoutDTO checkinCheckoutDTO = CheckinCheckoutMapper.INSTANCE
					.toDto(savedCheckinCheckout);

			this.log.info(LogMessage.SAVE_SUCCESS, checkinCheckoutSaveDTO);

			return ResponseEntity.ok(checkinCheckoutDTO);
		} catch (final Exception e) {
			this.log.error(LogMessage.SAVE_FAIL, checkinCheckoutSaveDTO);
			throw e;
		}
	}

}
