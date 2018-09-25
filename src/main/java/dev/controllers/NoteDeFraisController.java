package dev.controllers;

import dev.repositories.NoteDeFraisRepo;

public class NoteDeFraisController {

	NoteDeFraisRepo noteDeFraisRepo;

	public NoteDeFraisController(NoteDeFraisRepo noteDeFraisRepo) {
		super();
		this.noteDeFraisRepo = noteDeFraisRepo;
	}

}
