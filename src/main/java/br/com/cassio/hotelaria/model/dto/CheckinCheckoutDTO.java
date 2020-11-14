package br.com.cassio.hotelaria.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckinCheckoutDTO {

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date checkin;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date checkout;

	private Long idPessoa;

}
