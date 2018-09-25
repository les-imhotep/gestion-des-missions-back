package dev.controllers;

import dev.repositories.PrimeRepo;

public class PrimeController {

	PrimeRepo primeRepo;

	public PrimeController(PrimeRepo primeRepo) {
		super();
		this.primeRepo = primeRepo;
	}

}
