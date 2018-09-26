package dev.controller.dto;

import java.time.format.DateTimeFormatter;

import dev.entities.NatureMission;
import dev.entities.NoteDeFrais;
import dev.entities.enumerations.Transport;

public class NoteDeFraisDto {
	private Long id;
	private String dateDebut;
	private String dateFin;
	private NatureMission natureMission;
	private String villeDepart;
	private String villeArrivee;
	private Transport transport;
	private double frais;

	public NoteDeFraisDto() {
		super();
	}

	public NoteDeFraisDto(NoteDeFrais noteDeFrais) {
		super();
		this.id = noteDeFrais.getId();
		this.dateDebut = noteDeFrais.getDateDebut().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		this.dateFin = noteDeFrais.getDateFin().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		this.natureMission = noteDeFrais.getNatureMission();
		this.villeDepart = noteDeFrais.getVilleDepart();
		this.villeArrivee = noteDeFrais.getVilleArrivee();
		this.transport = noteDeFrais.getTransport();
		this.frais = noteDeFrais.getFrais();
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public NatureMission getNatureMission() {
		return natureMission;
	}

	public void setNatureMission(NatureMission natureMission) {
		this.natureMission = natureMission;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public double getFrais() {
		return frais;
	}

	public void setFrais(double frais) {
		this.frais = frais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
