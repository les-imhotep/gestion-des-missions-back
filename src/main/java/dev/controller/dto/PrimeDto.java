package dev.controller.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import dev.entities.NatureMission;

public class PrimeDto {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	private NatureMission natureMission;
	private double montant;

	public PrimeDto() {
		super();
	}

	public PrimeDto(LocalDate dateDebut, LocalDate dateFin, NatureMission natureMission, double montant) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.natureMission = natureMission;
		this.montant = montant;
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

	public NatureMission getNatureMission() {
		return natureMission;
	}

	public void setNatureMission(NatureMission natureMission) {
		this.natureMission = natureMission;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

}