package dev.controllers;

import dev.repositories.MissionRepo;

public class MissionController {

	MissionRepo missionRepo;

	public MissionController(MissionRepo missionRepo) {
		this.missionRepo = missionRepo;
	}

}
