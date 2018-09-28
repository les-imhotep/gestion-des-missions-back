package dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.entities.LigneDeFrais;
import dev.repositories.LigneDeFraisRepo;

@Service
public class LigneDeFraisService {

	private LigneDeFraisRepo ligneDeFraisRepo;

	public LigneDeFraisService(LigneDeFraisRepo ligneDeFraisRepo) {
		super();
		this.ligneDeFraisRepo = ligneDeFraisRepo;
	}

	public List<LigneDeFrais> listerLigneDeFrais() {
		return this.ligneDeFraisRepo.findAll();
	}

	public void addLigneDefraisMission(LigneDeFrais ligneDeFraisRepo) {

		this.ligneDeFraisRepo.save(ligneDeFraisRepo);

	}

}
