package dev.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.entities.Collegue;
import dev.entities.Mission;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.exceptions.InvalidDateException;
import dev.exceptions.InvalidDateMissionsException;
import dev.exceptions.InvalidDateTransportMissionException;
import dev.exceptions.InvalidIdException;
import dev.exceptions.InvalidIdMissionException;
import dev.repositories.CollegueRepo;
import dev.repositories.MissionRepo;

@Service
public class MissionService {

	@Autowired
	private MissionRepo missionRepo;

	@Autowired
	private CollegueRepo collegueRepo;

	public MissionService() {
		super();
	}

	public List<Mission> listerMission() {
		return this.missionRepo.findAll();

	}

	public List<Mission> findAllMission(String username) {

		return this.missionRepo.findAllByCollegueEmail(username);

	}

	public void newMission(Mission mission) {
		if (mission.getDateDebut().equals(LocalDate.now()) && mission.getDateDebut().isBefore(LocalDate.now())) {
			throw new InvalidDateMissionsException();

		} else if (mission.getTransport().equals(Transport.AVION)
				&& mission.getDateDebut().isBefore(LocalDate.now().plusDays(7))) {
			throw new InvalidDateTransportMissionException();

		} else if (mission.getDateFin().isBefore(mission.getDateDebut())) {
			throw new InvalidDateMissionsException();

		} else if ((mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SATURDAY)
						&& (mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SUNDAY)
								|| mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SATURDAY)))) {
			throw new InvalidDateException();
		} else {
			String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Optional<Collegue> optCollegue = this.collegueRepo.findByEmail(email);
			if (optCollegue.isPresent()) {
				mission.setCollegue(optCollegue.get());
			}
			// this.natureMissionRepo.findByName(mission.getNatureMission())
			mission.setStatut(Statut.INITIALE);
			this.missionRepo.save(mission);

		}

	}

	public void deleteMission(Mission mission) {
		if (this.missionRepo.existsById(mission.getId())) {
			this.missionRepo.delete(mission);

		} else {
			throw new InvalidIdException();
		}

	}

	public void updateMission(Mission missionAModifier) {

		Mission missionModifie = new Mission();
		// trouver la mission à modifier
		if (this.missionRepo.existsById(missionAModifier.getId())) {

			missionModifie.setDateDebut(missionAModifier.getDateDebut());
			missionModifie.setDateFin(missionAModifier.getDateFin());
			missionModifie.setNatureMission(missionAModifier.getNatureMission());
			missionModifie.setTransport(missionAModifier.getTransport());
			missionModifie.setVilleArrivee(missionAModifier.getVilleArrivee());
			missionModifie.setVilleDepart(missionAModifier.getVilleDepart());
			missionModifie.setStatut(Statut.INITIALE);
			this.missionRepo.save(missionModifie);

		} else {
			throw new InvalidIdMissionException();

		}
	}
}