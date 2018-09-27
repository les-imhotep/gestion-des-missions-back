package dev.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.services.MissionService;

@RestController
public class MissionController {

	MissionService missionService;

	public MissionController(MissionService missionService) {
		this.missionService = missionService;
	}

}
