package dev.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.controller.dto.NatureMissionDto;
import dev.entities.NatureMission;
import dev.exceptions.InvalidFacturationException;
import dev.exceptions.InvalidIdException;
import dev.exceptions.InvalidNameException;
import dev.exceptions.NameAllreadyExcistsException;
import dev.exceptions.PourcentageException;
import dev.repositories.NatureMissionRepo;

/**
 * @author Diginamic-02
 *
 */
@Service
public class NatureMissionService {
	private NatureMissionRepo natureMissionRepo;

	public NatureMissionService(NatureMissionRepo natureMissionRepo) {
		this.natureMissionRepo = natureMissionRepo;
	}

	/**
	 * lister toutes les natures de mission
	 * 
	 * @return la liste des natures de mission si sa date de fin n'est pas
	 *         passée
	 */
	public List<NatureMission> findAllNatureMission() {
		return natureMissionRepo.findAll().stream().filter(natureMission -> natureMission.getDateFin() == null)
				.collect(Collectors.toList());

	}

	/**
	 * supprimer une nature de mission
	 * 
	 * @param natureMission
	 *            nature de mission à supprimer
	 */
	public void deleteNatureMission(NatureMission natureMission) {
		if (this.natureMissionRepo.existsById(natureMission.getId())) {
			this.natureMissionRepo.delete(natureMission);

		} else {
			throw new InvalidIdException();
		}

	}

	/**
	 * sauvegarde une nouvelle nature de mission
	 * 
	 * @param natureMission
	 *            nouvelle nature de mission
	 * 
	 */
	public void addNatureMission(NatureMission natureMission) {
		if (natureMission.getFacturation() == null) {
			throw new InvalidFacturationException();
		}
		if (natureMission.getName() == null) {
			throw new InvalidNameException();
		}
		if (this.natureMissionRepo.existsByName((natureMission.getName()))) {
			throw new NameAllreadyExcistsException();
		}
		if (natureMission.getPourcentage() > 10) {
			throw new PourcentageException();
		} else {
			this.natureMissionRepo.save(natureMission);

		}

	}

	public NatureMission updateNatureMission(NatureMissionDto natureMissionAModifier) {

		NatureMission natureMissionModifie = new NatureMission();
		// trouver la nature de mission à modifier
		if (this.natureMissionRepo.existsById(natureMissionAModifier.getId())) {

			this.natureMissionRepo.findById(natureMissionAModifier.getId())
					.ifPresent(nature -> nature.setDateFin(LocalDate.now()));
			natureMissionModifie.setTjm(natureMissionAModifier.getTjm());
			natureMissionModifie.setPrime(natureMissionAModifier.isPrime());
		} else {
			throw new InvalidIdException();
		}

		if (natureMissionAModifier.getFacturation() == null) {
			throw new InvalidFacturationException();
		} else {
			natureMissionModifie.setFacturation(natureMissionAModifier.getFacturation());

		}
		if (natureMissionAModifier.getName() == null) {
			throw new InvalidNameException();
		} else {
			natureMissionModifie.setName(natureMissionAModifier.getName());
		}

		if (natureMissionAModifier.getPourcentage() > 10) {
			throw new PourcentageException();
		} else {
			natureMissionModifie.setPourcentage(natureMissionAModifier.getPourcentage());

		}
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
