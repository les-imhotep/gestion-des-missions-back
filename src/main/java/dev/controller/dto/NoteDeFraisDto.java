package dev.controller.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import dev.entities.NatureMission;
import dev.entities.enumerations.Transport;

public class NoteDeFraisDto {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	private NatureMission natureMission;
	private String villeDepart;
	private String villeArrivee;
	private Transport transport;
	private double frais;

	public NoteDeFraisDto() {
		super();
	}

	public NoteDeFraisDto(LocalDate dateDebut, LocalDate dateFin, NatureMission natureMission, String villeDepart,
			String villeArrivee, Transport transport, double frais) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.natureMission = natureMission;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.frais = frais;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
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

}
