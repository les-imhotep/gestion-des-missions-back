package dev.controller.dto;

import java.time.format.DateTimeFormatter;

import dev.entities.Absence;
import dev.entities.Collegue;

public class AbsenceDto {
	private Long id;
	private String dateDebut;
	private String dateFin;
	private Collegue collegue;

	public AbsenceDto() {
		super();
	}

	public AbsenceDto(Absence absence) {
		super();
		this.dateDebut = absence.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		this.dateFin = absence.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		this.collegue = absence.getCollegue();
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
