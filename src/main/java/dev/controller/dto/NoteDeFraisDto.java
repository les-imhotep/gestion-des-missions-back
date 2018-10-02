package dev.controller.dto;

import java.util.List;

import dev.entities.LigneDeFrais;
import dev.entities.NoteDeFrais;

public class NoteDeFraisDto {
	private Long id;
	private List<LigneDeFrais> lignesDeFrais;

	public NoteDeFraisDto() {
		super();
	}

	public NoteDeFraisDto(NoteDeFrais noteDeFrais) {
		super();

		this.id = noteDeFrais.getId();
		this.lignesDeFrais = noteDeFrais.getLignesDeFrais();

	}

	public NoteDeFraisDto(Long id, List<LigneDeFrais> lignesDeFrais) {
		super();
		this.id = id;
		this.lignesDeFrais = lignesDeFrais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LigneDeFrais> getLignesDeFrais() {
		return lignesDeFrais;
	}

	public void setLignesDeFrais(List<LigneDeFrais> lignesDeFrais) {
		this.lignesDeFrais = lignesDeFrais;
	}

}
