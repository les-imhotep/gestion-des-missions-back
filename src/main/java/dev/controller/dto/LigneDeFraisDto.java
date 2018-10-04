package dev.controller.dto;

import java.time.format.DateTimeFormatter;

import dev.entities.LigneDeFrais;
import dev.entities.enumerations.NatureLigne;

public class LigneDeFraisDto {
	private Long id;
	private String date;
	private NatureLigne natureLigne;
	private double frais;

	public LigneDeFraisDto() {
		super();
	}

	public LigneDeFraisDto(LigneDeFrais noteDeFrais) {
		super();
		this.id = noteDeFrais.getId();
		this.date = noteDeFrais.getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		this.natureLigne = noteDeFrais.getNatureLigne();
		this.frais = noteDeFrais.getFrais();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
