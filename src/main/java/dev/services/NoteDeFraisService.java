package dev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.entities.NoteDeFrais;
import dev.repositories.NoteDeFraisRepo;

@Service
public class NoteDeFraisService {

	private NoteDeFraisRepo noteDeFraisRepo;

	public NoteDeFraisService(NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

	public List<NoteDeFrais> listerNoteDeFrais() {
		return this.noteDeFraisRepo.findAll();
	}

	public void addNoteDefraisMission(NoteDeFrais noteDeFraisRepo) {

		this.noteDeFraisRepo.save(noteDeFraisRepo);

	}

}
