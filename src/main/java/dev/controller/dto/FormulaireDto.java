package dev.controller.dto;

import dev.entities.enumerations.Facturation;

public class FormulaireDto {
	private String name;
	private Facturation facturation;
	private boolean prime;
	private double tjm;
	private double pourcentage;

	public FormulaireDto() {
		super();
	}

	public FormulaireDto(String name, Facturation facturation, boolean prime, double tjm, double pourcentage) {
		super();
		this.name = name;
		this.facturation = facturation;
		this.prime = prime;
		this.tjm = tjm;
		this.pourcentage = pourcentage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
