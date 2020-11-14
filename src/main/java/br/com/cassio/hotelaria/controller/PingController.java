package br.com.cassio.hotelaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "api/v1/ping")
@Slf4j
public class PingController {

	@GetMapping
	public ResponseEntity<?> ping() {
		log.info("Ping");
		return ResponseEntity.noContent().build();
	}
}
