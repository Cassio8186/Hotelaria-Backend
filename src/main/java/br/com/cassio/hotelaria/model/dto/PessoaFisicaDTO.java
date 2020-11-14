package br.com.cassio.hotelaria.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cassio.hotelaria.model.enumeration.TipoPessoa;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaDTO {

	private Long id;

	@ApiModelProperty(example = "\"04069011048\"")
	private String cpf;

	@ApiModelProperty(example = "Jorgin")
	private String nome;

	@ApiModelProperty(example = "meuemail@gmail.com")
	private String email;

	@ApiModelProperty(example = "\"4523488\"")
	private String rg;

	@ApiModelProperty(example = "SEGUP")
	private String orgaoEmissor;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(example = "2004-05-12")
	private LocalDate dataEmissaoRg;

	@ApiModelProperty(example = "Solteiro")
	private String estadoCivil;

	@ApiModelProperty(example = "Masculino")
	private String genero;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(example = "2005-05-12")
	private LocalDate dataNascimento;

	@ApiModelProperty(example = "FISICA")
	private TipoPessoa tipoPessoa;

}
