package dev.controller.dto;

import java.time.format.DateTimeFormatter;

import dev.entities.NatureMission;
import dev.entities.Prime;

public class PrimeDto {
	private Long id;
	private String dateDebut;
	private String dateFin;
	private NatureMission natureMission;
	private double montant;

	public PrimeDto() {
		super();
	}

	public PrimeDto(Prime prime) {
		super();
		this.id = prime.getId();
		this.dateDebut = prime.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		;
		this.dateFin = prime.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		;
		this.natureMission = prime.getNatureMission();
		this.montant = prime.getMontant();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
