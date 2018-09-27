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
import dev.controller.dto.NatureMissionDto;
import dev.services.NatureMissionService;

@RestController() // le responsebody inclut permet de traduire en json
@CrossOrigin
@RequestMapping("/naturemission")
public class NatureMissionController {

	NatureMissionService natureMissionService;

	public NatureMissionController(NatureMissionService natureMissionService) {
		super();
		this.natureMissionService = natureMissionService;
	}

	@GetMapping()
	public ResponseEntity<List<NatureMissionDto>> findAllNatureMission() {

		return ResponseEntity.ok(this.natureMissionService.findAllNatureMission().stream()
				.map(col -> Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(col)).collect(Collectors.toList()));
	}

	@PostMapping("/new")
	public ResponseEntity<?> addNatureMission(@RequestBody NatureMissionDto natureMission) {

		return ResponseEntity.ok(this.natureMissionService
				.addNatureMission(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(natureMission)));

	}

	@PostMapping("/delete")
	public ResponseEntity<String> deleteNatureMission(@RequestBody NatureMissionDto natureMission) {

		return ResponseEntity.ok(this.natureMissionService
				.deleteNatureMission(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(natureMission)));
	}

	@PostMapping("/update")
	public ResponseEntity<NatureMissionDto> updateNatureMission(@RequestBody NatureMissionDto natureMission) {
		return ResponseEntity.ok(Converters.NATUREMISSION_TO_NATUREMISSION_DTO
				.convert(this.natureMissionService.updateNatureMission(natureMission)));

	}

}
