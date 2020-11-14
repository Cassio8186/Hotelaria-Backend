package br.com.cassio.hotelaria.controller.exceptions;

import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import lombok.Getter;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3416314487848402995L;
	private final String simpleErrorMessage;

	@SuppressWarnings("rawtypes")
	public EntityAlreadyExistsException(Class entity, String field, String rule, String simpleErrorMessage) {
		super(LogHelper.construct(LogMessage.ENTITY_ALREADY_EXISTS, entity.getSimpleName(), field, rule));
		this.simpleErrorMessage = simpleErrorMessage;
	}
}
