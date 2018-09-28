package dev.controller.dto;

import java.time.format.DateTimeFormatter;

import dev.entities.Collegue;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;

public class MissionDto {
	private Long id;
	private String dateDebut;
	private String dateFin;
	private NatureMission natureMission;
	private String villeDepart;
	private String villeArrivee;
	private Transport transport;
	private Statut statut;
	private double prime;
	private Collegue collegue;

	public MissionDto() {
		super();
	}

	public MissionDto(Mission mission) {
		super();
		this.id = mission.getId();
		this.dateDebut = mission.getDateDebut().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
		this.dateFin = mission.getDateFin().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
		this.natureMission = mission.getNatureMission();
		this.villeDepart = mission.getVilleDepart();
		this.villeArrivee = mission.getVilleArrivee();
		this.transport = mission.getTransport();
		this.statut = mission.getStatut();
		this.prime = mission.getPrime();
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

}
