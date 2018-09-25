package dev.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import dev.controller.dto.MissionDto;
import dev.services.MissionService;

public class MissionController {

	private MissionService service;

	public MissionController(MissionService service) {
		super();
		this.service = service;

	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<List<MissionDto>> findAllMission() {
		return ResponseEntity.ok(this.service.listerMission().stream()
				.map(mission -> dev.Converters.MISSION_TO_MISSION_DTO.convert(mission)).collect(Collectors.toList()));

	}

}
