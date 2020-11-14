package br.com.cassio.hotelaria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "checkin_checkout")
public class CheckinCheckout implements Serializable {

	private static final long serialVersionUID = 6207653409182259190L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "checkin")
	@Temporal(TemporalType.DATE)
	private Date checkin;

	@Column(nullable = false, name = "checkout")
	@Temporal(TemporalType.DATE)
	private Date checkout;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", foreignKey = @ForeignKey(name = "FK_CHECKIN_CHECKOUT_ID_PESSOA"))
	private Pessoa pessoa;

}