package dev.controller.dto;

import java.util.List;

import dev.entities.LigneDeFrais;
import dev.entities.NoteDeFrais;

public class NoteDeFraisDto {
	private Long id;
	private List<LigneDeFrais> lignesDeFrais;
	private MissionDto mission;

	public NoteDeFraisDto() {
		super();
	}

	public NoteDeFraisDto(NoteDeFrais noteDeFrais) {
		super();

		this.id = noteDeFrais.getId();
		this.lignesDeFrais = noteDeFrais.getLignesDeFrais();
		this.mission = dev.Converters.MISSION_TO_MISSION_DTO.convert(noteDeFrais.getMission());

	}

	public NoteDeFraisDto(Long id, List<LigneDeFrais> lignesDeFrais, MissionDto mission) {
		super();
		this.id = id;
		this.mission = mission;
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

	public MissionDto getMission() {
		return mission;
	}

	public void setMission(MissionDto mission) {
		this.mission = mission;
	}

}
