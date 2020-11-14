package br.com.cassio.hotelaria.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import br.com.cassio.hotelaria.model.PessoaFisica;
import br.com.cassio.hotelaria.model.dto.PessoaFisicaDTO;
import br.com.cassio.hotelaria.model.dto.PessoaFisicaSaveDTO;
import br.com.cassio.hotelaria.model.dto.PessoaFisicaUpdateDTO;
import br.com.cassio.hotelaria.model.mapper.PessoaFisicaMapper;
import br.com.cassio.hotelaria.model.mapper.PessoaFisicaSaveMapper;
import br.com.cassio.hotelaria.model.mapper.PessoaFisicaUpdateMapper;
import br.com.cassio.hotelaria.service.PessoaFisicaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/v1/pessoaFisicas")
public class PessoaFisicaController {
	private final LogHelper<PessoaFisicaController> log = new LogHelper<>(PessoaFisicaController.class);
	private PessoaFisicaService pessoaFisicaService;

	public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
		this.pessoaFisicaService = pessoaFisicaService;
	}

	@GetMapping
	@ApiOperation("Retorna uma lista com todas as pessoas físicas")
	public ResponseEntity<List<PessoaFisicaDTO>> getAllPessoaFisica() {
		List<PessoaFisica> pessoasFisicas = this.pessoaFisicaService.findAllPessoasFisicas();

		List<PessoaFisicaDTO> pessoasFisicasDTO = PessoaFisicaMapper.INSTANCE.toDto(pessoasFisicas);
		return ResponseEntity.ok(pessoasFisicasDTO);
	}

	@DeleteMapping(value = "/{id-pessoaFisica}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Deleta uma pessoa física pelo id")
	public ResponseEntity<Void> deletePessoaFisica(@PathVariable("id-pessoaFisica") Long idPessoaFisica) {
		final String searchParameter = "pessoaFisica.id";

		log.info(LogMessage.DELETE_SOFT_REQUEST, searchParameter, idPessoaFisica);
		try {

			this.pessoaFisicaService.delete(idPessoaFisica);
			;
			log.info(LogMessage.DELETE_SOFT_SUCCESS, searchParameter, idPessoaFisica);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			log.error(LogMessage.DELETE_SOFT_FAIL, searchParameter, idPessoaFisica);
			throw e;
		}
	}

	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Retorna uma pessoa física pelo cpf")
	public ResponseEntity<PessoaFisicaDTO> findPessoaFisicaByPessoaCpf(@PathVariable("cpf") String cpf) {
		final String searchParameter = "pessoaFisica.cpf";

		log.info(LogMessage.FIND_BY_REQUEST, searchParameter, cpf);

		PessoaFisica pessoaFisica = this.pessoaFisicaService.findByPessoaCpf(cpf);
		PessoaFisicaDTO pessoaFisicaDTO = PessoaFisicaMapper.INSTANCE.toDto(pessoaFisica);
		log.info(LogMessage.FIND_BY_SUCCESS, searchParameter, cpf);
		return ResponseEntity.ok(pessoaFisicaDTO);

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Salva uma pessoa física")
	public ResponseEntity<PessoaFisicaDTO> savePessoaFisica(
			@RequestBody @Validated PessoaFisicaSaveDTO pessoaFisicaSaveDTO) {
		log.info(LogMessage.SAVE_REQUEST, pessoaFisicaSaveDTO);
		try {
			PessoaFisica pessoaFisica = PessoaFisicaSaveMapper.INSTANCE.toEntity(pessoaFisicaSaveDTO);
			pessoaFisica = this.pessoaFisicaService.save(pessoaFisica);
			PessoaFisicaDTO pessoaFisicaDTO = PessoaFisicaMapper.INSTANCE.toDto(pessoaFisica);

			return ResponseEntity.ok(pessoaFisicaDTO);
		} catch (Exception e) {
			log.error(LogMessage.SAVE_FAIL, pessoaFisicaSaveDTO);
			throw e;
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Atualiza uma pessoa física pelo id")
	public ResponseEntity<PessoaFisicaDTO> updatePessoaFisica(
			@RequestBody @Validated PessoaFisicaUpdateDTO pessoaFisicaUpdateDTO) {
		log.info(LogMessage.UPDATE_REQUEST, pessoaFisicaUpdateDTO);
		try {
			PessoaFisica pessoaFisica = PessoaFisicaUpdateMapper.INSTANCE.toEntity(pessoaFisicaUpdateDTO);
			pessoaFisica = this.pessoaFisicaService.update(pessoaFisica);
			PessoaFisicaDTO pessoaFisicaDTO = PessoaFisicaMapper.INSTANCE.toDto(pessoaFisica);

			return ResponseEntity.ok(pessoaFisicaDTO);
		} catch (Exception e) {
			log.error(LogMessage.UPDATE_REQUEST, pessoaFisicaUpdateDTO);
			throw e;
		}
	}
}
