package br.com.cassio.hotelaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cassio.hotelaria"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {

		String email = "cassio8186@gmail.com";
		String name = "Cássio Lopes";
		String url = "https://github.com/Cassio8186";
		Contact contact = new Contact(name, url, email);

		return new ApiInfoBuilder()
				.title("Hotelaria Backend")
				.description("Aplicação Web de hotelaria desenvolvida para faculdade")
				.contact(contact)
				.build();
	}
}
