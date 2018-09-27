package dev.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.controller.dto.NoteDeFraisDto;
import dev.entities.enumerations.Transport;

@Entity

public class NoteDeFrais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	private NatureMission natureMission;
	private String villeDepart;
	private String villeArrivee;
	private Transport transport;
	private double frais;

	public NoteDeFrais() {
		super();
	}

	public NoteDeFrais(NoteDeFraisDto noteDeFrais) {
		super();
		this.id = noteDeFrais.getId();
		this.dateDebut = LocalDate.parse(noteDeFrais.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dateFin = LocalDate.parse(noteDeFrais.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.natureMission = noteDeFrais.getNatureMission();
		this.villeDepart = noteDeFrais.getVilleDepart();
		this.villeArrivee = noteDeFrais.getVilleArrivee();
		this.transport = noteDeFrais.getTransport();
		this.frais = noteDeFrais.getFrais();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
