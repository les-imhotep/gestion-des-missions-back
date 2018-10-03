package dev.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import dev.controller.dto.NoteDeFraisDto;

@Entity
public class NoteDeFrais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private List<LigneDeFrais> lignesDeFrais = new ArrayList<>();
	@OneToOne
	private Mission mission;

	public NoteDeFrais() {
		super();
	}

	public NoteDeFrais(NoteDeFraisDto noteDeFraisDto) {
		super();

		this.id = noteDeFraisDto.getId();
		this.lignesDeFrais = noteDeFraisDto.getLignesDeFrais();
		this.mission = noteDeFraisDto.getMission();

	}

	public NoteDeFrais(Long id, List<LigneDeFrais> lignesDeFrais, Mission mission) {
		super();
		this.id = id;
		this.lignesDeFrais = lignesDeFrais;
		this.mission = mission;
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

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

}
