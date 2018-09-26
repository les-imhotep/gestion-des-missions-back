package dev.services;

import java.util.List;

import dev.entities.NoteDeFrais;
import dev.repositories.NoteDeFraisRepo;

public class NoteDeFraisService {

	private NoteDeFraisRepo noteDeFraisRepo;

	public NoteDeFraisService(NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

	public List<NoteDeFrais> listerNoteDeFrais() {
		return this.noteDeFraisRepo.findAllNoteDeFrais();
	}
}
