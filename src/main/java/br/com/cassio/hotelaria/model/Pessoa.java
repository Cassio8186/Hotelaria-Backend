package br.com.cassio.hotelaria.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import br.com.cassio.hotelaria.model.enumeration.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -5938589468219956031L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email")
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa", insertable = false, updatable = false)
	private TipoPessoa tipoPessoa;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	private CheckinCheckout checkinCheckout;

	private Boolean excluir;

}