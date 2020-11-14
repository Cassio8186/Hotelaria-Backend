package br.com.cassio.hotelaria.config;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import br.com.cassio.hotelaria.helper.LogHelper;

@Component
public class StaticContextInitializer {

	private final MessageSource messageSource;

	public StaticContextInitializer(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@PostConstruct
	public void init() {
		LogHelper.init(messageSource);
	}
}
