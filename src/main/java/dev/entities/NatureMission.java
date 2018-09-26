package dev.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.controller.dto.NatureMissionDto;
import dev.entities.enumerations.Facturation;

@Entity
public class NatureMission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Facturation facturation;
	private boolean prime;
	private double tjm;
	private double pourcentage;

	public NatureMission() {
		super();
	}

	public NatureMission(NatureMissionDto natureMission) {
		super();
		this.id = natureMission.getId();
		this.facturation = natureMission.getFacturation();
		this.prime = natureMission.isPrime();
		this.tjm = natureMission.getTjm();
		this.pourcentage = natureMission.getPourcentage();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
