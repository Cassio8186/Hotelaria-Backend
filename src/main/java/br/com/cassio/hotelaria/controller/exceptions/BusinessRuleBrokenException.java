package br.com.cassio.hotelaria.controller.exceptions;

import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import lombok.Getter;

@Getter
public class BusinessRuleBrokenException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3416314487848402995L;

	private final String simpleErrorMessage;

	@SuppressWarnings("rawtypes")
	public BusinessRuleBrokenException(Class entity, String field, String rule) {
		super(LogHelper.construct(LogMessage.EXCEPTION_BUSINESS_RULE_BROKEN, entity.getSimpleName(), field, rule));
		this.simpleErrorMessage = rule;
	}
}
