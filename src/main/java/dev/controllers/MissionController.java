package dev.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

@CrossOrigin
@RestController() // @Controller + @ResponseBody
@RequestMapping("/missions")
public class MissionController extends AbstractController {

	private MissionService service;

	public MissionController(MissionService service) {
		super();
		this.service = service;

	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<List<MissionDto>> findAllMission() {
		String username = getUserDetails();
		return ResponseEntity.ok(this.service.findAllMission(username).stream()
				.map(mission -> dev.Converters.MISSION_TO_MISSION_DTO.convert(mission)).collect(Collectors.toList()));

	}

	// *************************************POST***********************************************
	@PostMapping(path = "/new")
	public void newMission(@RequestBody MissionDto missionDto) {
		MissionDto newMissionDto = new MissionDto();

		newMissionDto.setCollegue(missionDto.getCollegue());
		newMissionDto.setDateDebut(missionDto.getDateDebut());
		newMissionDto.setDateFin(missionDto.getDateFin());
		newMissionDto.setNatureMission(missionDto.getNatureMission());
		newMissionDto.setStatut(missionDto.getStatut());
		newMissionDto.setTransport(missionDto.getTransport());
		newMissionDto.setVilleArrivee(missionDto.getVilleArrivee());
		newMissionDto.setVilleDepart(missionDto.getVilleDepart());
		this.service.newMission(Converters.MISSION_DTO_TO_MISSION.convert(newMissionDto));

	}
}
