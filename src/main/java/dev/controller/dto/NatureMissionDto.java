package dev.controller.dto;

import dev.entities.enumerations.Facturation;

public class NatureMissionDto {
	private Facturation facturation;
	private boolean prime;
	private double tjm;
	private double pourcentage;

	public NatureMissionDto() {
		super();
	}

	public NatureMissionDto(Facturation facturation, boolean prime, double tjm, double pourcentage) {
		super();
		this.facturation = facturation;
		this.prime = prime;
		this.tjm = tjm;
		this.pourcentage = pourcentage;
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

}
