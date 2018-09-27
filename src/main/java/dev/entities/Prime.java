package dev.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.controller.dto.PrimeDto;

@Entity
public class Prime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	private NatureMission natureMission;
	private double montant;

	public Prime() {
		super();
	}

	public Prime(PrimeDto prime) {
		super();
		this.id = prime.getId();
		this.dateDebut = LocalDate.parse(prime.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dateFin = LocalDate.parse(prime.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.natureMission = prime.getNatureMission();
		this.montant = prime.getMontant();
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
