package br.com.cassio.hotelaria.controller.exceptions;

import br.com.cassio.hotelaria.helper.LogHelper;
import br.com.cassio.hotelaria.helper.enumeration.LogMessage;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4164430121080969278L;
	private final String simpleErrorMessage;

	@SuppressWarnings("rawtypes")
	public EntityNotFoundException(Class entity, String field, Object value, String simpleErrorMessage) {
		super(LogHelper.construct(LogMessage.EXCEPTION_ENTITY_NOT_FOUND, entity.getSimpleName(), field, value));
		this.simpleErrorMessage = simpleErrorMessage;

	}
}
