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
import dev.controller.dto.LigneDeFraisDto;
import dev.entities.LigneDeFrais;
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

	@PostMapping("/new")
	public ResponseEntity<?> addNatureMission(@RequestBody LigneDeFraisDto ligneDeFraisDto) {
		this.ligneDeFraisService.addLigneDefrais(Converters.LIGNEDEFRAIS_DTO_TO_LIGNEDEFRAIS.convert(ligneDeFraisDto));
		return findAllLigneDeFrais();

	}

	@PostMapping("/delete")
	public ResponseEntity<List<LigneDeFraisDto>> deleteNatureMission(@RequestBody LigneDeFraisDto ligneDeFraisDto) {
		this.ligneDeFraisService
				.deleteLigneDeFrais(Converters.LIGNEDEFRAIS_DTO_TO_LIGNEDEFRAIS.convert(ligneDeFraisDto));
		return findAllLigneDeFrais();
	}

	@PostMapping("/update")
	public ResponseEntity<LigneDeFraisDto> updateNatureMission(@RequestBody LigneDeFrais ligneDeFrais) {
		return ResponseEntity.ok(Converters.LIGNEDEFRAIS_TO_LIGNEDEFRAIS_DTO
				.convert(this.ligneDeFraisService.updateLigneDeFrais(ligneDeFrais)));

	}

}
