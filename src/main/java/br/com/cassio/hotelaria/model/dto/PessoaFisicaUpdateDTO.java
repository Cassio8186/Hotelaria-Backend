package br.com.cassio.hotelaria.model.dto;

import java.time.LocalDate;

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
public class PessoaFisicaUpdateDTO {
	@NotNull
	private Long id;

	@ApiModelProperty(example = "Jorgin do pop")
	@NotNull
	private String nome;

	@ApiModelProperty(example = "\"04069011048\"")
	@NotNull
	@CPF
	@Length(min = 11, max = 11, message = "CPF deve possuir 11 caracteres")
	private String cpf;

	@NotNull
	@ApiModelProperty(example = "\"4564577\"")
	private String rg;

	@NotNull
	@ApiModelProperty(example = "jorginpop@gmail.com")
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
