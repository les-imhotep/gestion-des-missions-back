package dev.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Converters;
import dev.controller.dto.MissionDto;
import dev.services.MissionService;

/**
 * controller pour les missions
 * 
 * @author Diginamic-02
 *
 */
@CrossOrigin
@RestController() // @Controller + @ResponseBody
@RequestMapping("/missions")
public class MissionController {

	@Autowired
	private MissionService service;

	public MissionController() {
		super();
	}

	// *************************************GET***********************************************

	@GetMapping
	public ResponseEntity<List<MissionDto>> findAllMission() {
		return ResponseEntity.ok(this.service.findAllMission().stream()
				.map(mission -> dev.Converters.MISSION_TO_MISSION_DTO.convert(mission)).collect(Collectors.toList()));

	}

	// *************************************POST***********************************************
	@PostMapping("/new")
	public void newMission(@RequestBody MissionDto missionDto) throws ParseException {
		// Formatter la date envoyer au format yyyy-MM-dd vers le format dto
		// dd-MM-yyyy

		this.service.newMission(Converters.MISSION_DTO_TO_MISSION.convert(missionDto));

	}

	@PostMapping("/delete")
	public ResponseEntity<List<MissionDto>> deleteMission(@RequestBody MissionDto missionDto) {
		this.service.deleteMission(Converters.MISSION_DTO_TO_MISSION.convert(missionDto));
		return findAllMission();
	}

	@PostMapping("/update")
	public void updateMission(@RequestBody MissionDto missionDto) {
		this.service.updateMission(Converters.MISSION_DTO_TO_MISSION.convert(missionDto));

	}
}
