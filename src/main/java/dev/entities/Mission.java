package dev.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.controller.dto.MissionDto;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;

@Entity
public class Mission {
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
	private Statut statut;
	private double prime;
	@ManyToOne
	private Collegue collegue;

	public Mission() {
		super();
	}

	public Mission(MissionDto mission) {
		super();
		this.id = mission.getId();
		this.dateDebut = LocalDate.parse(mission.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dateFin = LocalDate.parse(mission.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.natureMission = mission.getNatureMission();
		this.villeDepart = mission.getVilleDepart();
		this.villeArrivee = mission.getVilleArrivee();
		this.transport = mission.getTransport();
		this.statut = mission.getStatut();
		this.prime = mission.getPrime();
		this.collegue = mission.getCollegue();
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
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

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public double getPrime() {
		return prime;
	}

	public void setPrime(double prime) {
		this.prime = prime;
	}

}
