package dev.controllers;

import org.springframework.web.bind.annotation.RestController;

import dev.services.AbsenceService;

@RestController
public class AbsenceController {
	AbsenceService absenceService;

	public AbsenceController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}
}
