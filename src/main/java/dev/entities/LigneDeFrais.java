package dev.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.controller.dto.LigneDeFraisDto;
import dev.entities.enumerations.NatureLigne;

@Entity

public class LigneDeFrais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private NatureLigne natureLigne;
	private double frais;

	public LigneDeFrais() {
		super();
	}

	public LigneDeFrais(LigneDeFraisDto noteDeFrais) {
		super();
		this.id = noteDeFrais.getId();
		this.natureLigne = noteDeFrais.getNatureLigne();
		this.frais = noteDeFrais.getFrais();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public NatureLigne getNatureLigne() {
		return natureLigne;
	}

	public void setNatureLigne(NatureLigne natureLigne) {
		this.natureLigne = natureLigne;
	}

	public double getFrais() {
		return frais;
	}

	public void setFrais(double frais) {
		this.frais = frais;
	}

}
