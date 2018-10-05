package dev.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.services.MissionService;

/**
 * Controller pour les primes
 * 
 * @author Diginamic-02
 *
 */
@CrossOrigin
@RestController()
@RequestMapping("/primes")
public class PrimeController {

	MissionService missionService;

	public PrimeController(MissionService missionService) {
		super();
		this.missionService = missionService;
	}

	// *************************************GET***********************************************
	@GetMapping
	public ResponseEntity<?> findAllPrime() {
		return ResponseEntity.ok(this.missionService.findAllPrimes());
	}

	@GetMapping("/ddl")
	public void ddlPrime(HttpServletResponse response) throws IOException {
		Workbook file = this.missionService.ddlPrime();

		response.setContentType("application/octet-stream");

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + "primes "
				+ this.missionService.findAllMission().get(0).getCollegue().getNom() + ".xlsx" + "\""));

		file.write(response.getOutputStream());

	}

}
