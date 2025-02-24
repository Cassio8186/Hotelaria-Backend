package br.com.cassio.hotelaria.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class SwaggerFilter implements Filter {

	private final String DOCUMENTATION_URL = "/swagger-ui.html";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (request.getRequestURI().equals("") || request.getRequestURI().equals("/")) {
			response.sendRedirect(DOCUMENTATION_URL);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
