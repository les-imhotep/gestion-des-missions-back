package dev.controllers;

import dev.repositories.AbsenceRepo;

public class AbsenceController {
	AbsenceRepo absenceRepo;

	public AbsenceController(AbsenceRepo absenceRepo) {
		this.absenceRepo = absenceRepo;
	}
}
