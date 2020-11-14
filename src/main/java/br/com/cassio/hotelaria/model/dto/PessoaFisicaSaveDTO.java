package br.com.cassio.hotelaria.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaSaveDTO {
	@ApiModelProperty(example = "Jorgin")
	@NotNull
	private String nome;

	@ApiModelProperty(example = "\"04069011048\"")
	@Length(min = 11, max = 11, message = "CPF deve possuir 11 caracteres")
	@CPF
	@NotNull
	private String cpf;

	@NotNull
	@ApiModelProperty(example = "\"4523488\"")
	private String rg;

	@NotNull
	@ApiModelProperty(example = "meuemail@gmail.com")
	@Email
	private String email;

	@NotNull
	@ApiModelProperty(example = "SEGUP")
	private String orgaoEmissor;

	@NotNull
	@ApiModelProperty(example = "Solteiro")
	private String estadoCivil;

	@NotNull
	@ApiModelProperty(example = "Masculino")
	private String genero;

	@NotNull
	@ApiModelProperty(example = "2005-05-12")
	private LocalDate dataEmissaoRg;

	@NotNull
	@ApiModelProperty(example = "1998-05-12")
	private LocalDate dataNascimento;

}
