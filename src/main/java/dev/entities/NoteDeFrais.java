package dev.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dev.controller.dto.NoteDeFraisDto;

@Entity
public class NoteDeFrais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private List<LigneDeFrais> lignesDeFrais;

	public NoteDeFrais() {
		super();
	}

	public NoteDeFrais(NoteDeFraisDto noteDeFraisDto) {
		super();

		this.id = noteDeFraisDto.getId();
		this.lignesDeFrais = noteDeFraisDto.getLignesDeFrais();

	}

	public NoteDeFrais(Long id, List<LigneDeFrais> lignesDeFrais) {
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
