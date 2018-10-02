package dev.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public List<Mission> findAllMission() {

		return this.missionRepo.findAllByCollegueEmail(getUserDetails());

	}

	public List<Mission> findMissionbyStatut(String statut) {

		return this.missionRepo.findAllByStatut(statut);

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

	@Scheduled(fixedRate = 1000)
	public void Test() {

		List<Mission> missions = listerMission().stream().filter(mission -> mission.getStatut().equals(Statut.INITIALE))
				.collect(Collectors.toList());

		missions.forEach(element -> {
			element.setStatut(Statut.EN_ATTENTE_VALIDATION);
			this.missionRepo.save(element);
			List<Mission> missionByDateFinEchu = listerMission().stream()
					.filter(missionDate -> missionDate.getDateFin().isBefore(LocalDate.now()))
					.collect(Collectors.toList());

			missionByDateFinEchu.forEach(mission -> {

				if (mission.getNatureMission().getDateFin() == null) {
					mission.setPrime(mission.getNatureMission().getPourcentage() * mission.getNatureMission().getTjm());
					this.missionRepo.save(mission);
				} else if ((mission.getNatureMission().getDateFin() != null)
						&& (mission.getDateDebut().isBefore(mission.getNatureMission().getDateFin()))) {
					mission.setPrime(mission.getNatureMission().getPourcentage() * mission.getNatureMission().getTjm());

					this.missionRepo.save(mission);
				}

			});

		});
		// envoie mail manager a faire
		// Prime = (nombre de jours travaillés)* TJM * %Prime/100
	}

	/**
	 * Cette methode s'execute toutes les nuits à 1h du matin
	 * 
	 * Ont recupere la liste de toute les missions Ont la filtre par Statut =>
	 * INITIALE Sur chaques elements de la liste ont set le statut a EN ATTENTE
	 * DE VALIDATION
	 * 
	 * @return void
	 */
	@Scheduled(cron = "1 * * * * *")
	public void TraitementDeNuit() {

		List<Mission> missions = listerMission().stream().filter(mission -> mission.getStatut().equals(Statut.INITIALE))
				.collect(Collectors.toList());

		missions.forEach(element -> {
			element.setStatut(Statut.EN_ATTENTE_VALIDATION);
			this.missionRepo.save(element);
		});
		System.out.println("cron success" + " " + LocalTime.now());
	}
}
