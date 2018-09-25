package dev.entities;

import java.time.LocalDate;

public class Absence {

	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Collegue collegue;

	public Absence() {
		super();
	}

	public Absence(Long id, LocalDate dateDebut, LocalDate dateFin, Collegue collegue) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.collegue = collegue;
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