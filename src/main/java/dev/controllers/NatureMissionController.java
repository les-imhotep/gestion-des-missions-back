package dev.controllers;

import dev.repositories.NatureMissionRepo;

public class NatureMissionController {

	NatureMissionRepo natureMissionRepo;

	public NatureMissionController(NatureMissionRepo natureMissionRepo) {
		super();
		this.natureMissionRepo = natureMissionRepo;
	}

}
