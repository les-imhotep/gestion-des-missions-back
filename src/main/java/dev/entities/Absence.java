package dev.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.controller.dto.AbsenceDto;

@Entity
public class Absence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;

	@ManyToOne
	private Collegue collegue;

	public Absence() {
		super();
	}

	public Absence(AbsenceDto absence) {
		super();
		this.id = absence.getId();
		this.dateDebut = LocalDate.parse(absence.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dateFin = LocalDate.parse(absence.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		this.collegue = absence.getCollegue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
