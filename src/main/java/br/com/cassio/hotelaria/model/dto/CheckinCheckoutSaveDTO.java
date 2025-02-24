package br.com.cassio.hotelaria.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckinCheckoutSaveDTO {

	@NotNull
	private Date checkin;

	@NotNull
	private Date checkout;

	@NotNull
	private Long idPessoa;

}
