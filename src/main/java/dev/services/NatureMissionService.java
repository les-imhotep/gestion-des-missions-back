package dev.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.NatureMissionDto;
import dev.entities.NatureMission;
import dev.exceptions.InvalidIdException;
import dev.repositories.NatureMissionRepo;

@Service
public class NatureMissionService {
	private NatureMissionRepo natureMissionRepo;

	public NatureMissionService(NatureMissionRepo natureMissionRepo) {
		this.natureMissionRepo = natureMissionRepo;
	}

	public List<NatureMission> findAllNatureMission() {
		return natureMissionRepo.findAll().stream().filter(natureMission -> natureMission.getDateFin() == null)
				.collect(Collectors.toList());

	}

	public String deleteNatureMission(NatureMission natureMission) {
		if (this.natureMissionRepo.existsById(natureMission.getId())) {
			this.natureMissionRepo.delete(natureMission);
			return "Nature de mission supprimé";
		} else {
			throw new InvalidIdException();
		}

	}

	public String addNatureMission(NatureMission natureMission) {
		if (existsByName(natureMission.getName())) {
			return "Nature déjà existante";
		} else {
			this.natureMissionRepo.save(natureMission);
			return "Nature de mission ajouté";
		}

	}

	public NatureMission updateNatureMission(NatureMissionDto natureMissionAModifier) {
		// trouver la nature de mission à modifier
		if (this.natureMissionRepo.existsById(natureMissionAModifier.getId())) {
			this.natureMissionRepo.findById(natureMissionAModifier.getId())
					.ifPresent(nature -> nature.setDateFin(LocalDate.now()));
			// this.natureMissionRepo.save(natureMissionAModifier);

		} else {
			throw new InvalidIdException();
		}

		NatureMission natureMissionModifie = new NatureMission();

		natureMissionModifie.setFacturation(natureMissionAModifier.getFacturation());
		natureMissionModifie.setName(natureMissionAModifier.getName());
		natureMissionModifie.setPourcentage(natureMissionAModifier.getPourcentage());
		natureMissionModifie.setTjm(natureMissionAModifier.getTjm());
		natureMissionModifie.setPrime(natureMissionAModifier.isPrime());
		this.natureMissionRepo.save(natureMissionModifie);

		return natureMissionModifie;
	}

	public boolean existsByName(String name) {
		if (this.natureMissionRepo.existsByName(name)) {
			return true;
		}

		else {
			return false;
		}

	}
}
