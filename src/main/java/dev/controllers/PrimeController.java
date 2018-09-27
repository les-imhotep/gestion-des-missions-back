package dev.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.services.PrimeService;

@RestController
public class PrimeController {

	PrimeService primeService;

	public PrimeController(PrimeService primeService) {
		super();
		this.primeService = primeService;
	}

}
