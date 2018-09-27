package dev.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Converters;
import dev.controller.dto.NoteDeFraisDto;
import dev.services.NoteDeFraisService;

@CrossOrigin
@RestController()
@RequestMapping("/notedefrais")

public class NoteDeFraisController {

	NoteDeFraisService noteDeFraisService;

	public NoteDeFraisController(NoteDeFraisService noteDeFraisService) {
		super();
		this.noteDeFraisService = noteDeFraisService;
	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<List<NoteDeFraisDto>> findAllNoteDeFrais() {
		return ResponseEntity.ok(this.noteDeFraisService.listerNoteDeFrais().stream()
				.map(col -> Converters.NOTEDEFRAIS_TO_NOTEDEFRAIS_DTO.convert(col)).collect(Collectors.toList()));
	}

}
