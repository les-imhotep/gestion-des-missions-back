package dev.controller.dto;

import java.time.LocalDate;

import dev.entities.Collegue;

public class AbsenceDto {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Collegue collegue;

	public AbsenceDto() {
		super();
	}

	public AbsenceDto(LocalDate dateDebut, LocalDate dateFin, Collegue collegue) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.collegue = collegue;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

}
