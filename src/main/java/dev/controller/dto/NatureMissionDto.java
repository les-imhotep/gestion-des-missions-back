package dev.controller.dto;

import java.time.LocalDate;

import dev.entities.NatureMission;
import dev.entities.enumerations.Facturation;

public class NatureMissionDto {
	private Long id;
	private Facturation facturation;
	private boolean prime;
	private double tjm;
	private double pourcentage;
	private LocalDate dateFin;
	private String name;

	public NatureMissionDto() {
		super();
	}

	public NatureMissionDto(NatureMission natureMission) {
		super();
		this.id = natureMission.getId();
		this.facturation = natureMission.getFacturation();
		this.prime = natureMission.isPrime();
		this.tjm = natureMission.getTjm();
		this.pourcentage = natureMission.getPourcentage();
		this.name = natureMission.getName();
		this.dateFin = natureMission.getDateFin();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
