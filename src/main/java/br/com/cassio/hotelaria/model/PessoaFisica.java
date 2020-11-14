package br.com.cassio.hotelaria.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "FISICA")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 5227590821171385282L;

	@Column
	private String nome;

	@Column(length = 11, name = "cpf")
	private String cpf;

	@Column(name = "rg")
	private String rg;

	@Column(name = "orgao_emissor")
	private String orgaoEmissor;

	@Column(name = "data_emissao_rg")
	private LocalDate dataEmissaoRg;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Column(name = "genero")
	private String genero;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

}