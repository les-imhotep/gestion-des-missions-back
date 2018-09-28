package dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.entities.Prime;
import dev.repositories.PrimeRepo;

@Service
public class PrimeService {
	private PrimeRepo primeRepo;

	public PrimeService(PrimeRepo primeRepo) {
		this.primeRepo = primeRepo;
	}

	public List<Prime> findAllPrimeMission() {
		return primeRepo.findAll().stream().filter(prime -> prime.getDateFin() == null).collect(Collectors.toList());

	}
}
