package br.com.cassio.hotelaria.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import br.com.cassio.hotelaria.helper.enumeration.LogMessage;

public class LogHelper<T> {

	private static MessageSource messageSource;

	public static String construct(LogMessage logMessage, Object... values) {
		return messageSource.getMessage(logMessage.getValue(), values, LocaleContextHolder.getLocale());
	}

	public static void init(MessageSource messageSource) {
		LogHelper.messageSource = messageSource;
	}

	private final Logger log;

	public LogHelper(Class<T> entity) {
		this.log = LoggerFactory.getLogger(entity);
	}

	public void error(LogMessage logMessage, Object... values) {
		log.error(construct(logMessage, values));
	}

	public void info(LogMessage logMessage, Object... values) {
		log.info(construct(logMessage, values));
	}
}
