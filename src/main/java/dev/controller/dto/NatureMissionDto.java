package dev.controller.dto;

import java.time.LocalDate;

import dev.entities.enumerations.Facturation;

public class NatureMissionDto {
	private Long id;
	private Facturation facturation;
	private boolean prime;
	private double tjm;
	private double pourcentage;
	private String name;
	private LocalDate dateFin;

	public NatureMissionDto() {
		super();
	}

	public NatureMissionDto(Long id, Facturation facturation, boolean prime, double tjm, double pourcentage,
			String name, LocalDate dateFin) {
		super();
		this.facturation = facturation;
		this.prime = prime;
		this.tjm = tjm;
		this.pourcentage = pourcentage;
		this.name = name;
		this.dateFin = dateFin;
		this.id = id;
	}

	public Facturation getFacturation() {
		return facturation;
	}

	public void setFacturation(Facturation facturation) {
		this.facturation = facturation;
	}

	public boolean isPrime() {
		return prime;
	}

	public void setPrime(boolean prime) {
		this.prime = prime;
	}

	public double getTjm() {
		return tjm;
	}

	public void setTjm(double tjm) {
		this.tjm = tjm;
	}

	public double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}