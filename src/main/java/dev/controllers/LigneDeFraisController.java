package dev.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Converters;
import dev.controller.dto.LigneDeFraisDto;
import dev.services.LigneDeFraisService;

/**
 * Controller pour les lignes de frais
 * 
 * @author Diginamic-02
 *
 */
@CrossOrigin
@RestController()
@RequestMapping("/lignesdefrais")

public class LigneDeFraisController {

	LigneDeFraisService ligneDeFraisService;

	public LigneDeFraisController(LigneDeFraisService ligneDeFraisService) {
		super();
		this.ligneDeFraisService = ligneDeFraisService;
	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<List<LigneDeFraisDto>> findAllLigneDeFrais() {
		return ResponseEntity.ok(this.ligneDeFraisService.listerLigneDeFrais().stream()
				.map(col -> Converters.LIGNEDEFRAIS_TO_LIGNEDEFRAIS_DTO.convert(col)).collect(Collectors.toList()));
	}

}
