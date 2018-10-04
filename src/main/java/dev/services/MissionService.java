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

	/**
	 * liste toutes les mission en base
	 * 
	 * @return la liste des mission en base
	 */
	public List<Mission> listerMission() {
		return this.missionRepo.findAll();

	}

	/**
	 * Methode permettant de trouver l'utilisteur connecté
	 * 
	 * @return Le user name de l'utilisateur connecter ici l'adresse mail
	 */
	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	/**
	 * Liste des mission d'un utilisteur connecté
	 * 
	 * @return return seulement les missions de l'utilisateur actuel connecté
	 */
	public List<Mission> findAllMission() {

		return this.missionRepo.findAllByCollegueEmail(getUserDetails());

	}

	/**
	 * liste les mission par statut
	 * 
	 * @param statut
	 * @return retourne que les missions du status, en attente de validation
	 */
	public List<Mission> findMissionbyStatut() {

		return this.missionRepo.findAllByStatut(Statut.EN_ATTENTE_VALIDATION);

	}

	public void updateStatutMission(Mission StatutAModifier) {
		if (this.missionRepo.existsById(StatutAModifier.getId())) {
			this.missionRepo.save(StatutAModifier);

		}
	}

	/**
	 * Je controle les parametre suivant : si le type de transport est l'avion,
	 * une anticipation de 7 jours est exigée sinon je renvoie une exception la
	 * date de fin est supérieure ou égale à la date de début sinon je renvoie
	 * une exception mission qui chevauche une autre mission sinon je renvoie
	 * une exception mission qui commence ou finit un jour non travaillé sinon
	 * je renvoie une exception
	 * 
	 * si tous les controle sont passant alors je set le statut de la mission a
	 * INITIALE et je sauvegarde la mission
	 * 
	 * @param mission
	 * @exception InvalidDateMissionsException()
	 * @exception InvalidDateTransportMissionException()
	 * @exception InvalidDateException()
	 * 
	 */
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

	/**
	 * Je verifie que la misison existe, si elle existe je la suprime
	 * 
	 * @param mission
	 */
	public void deleteMission(Mission mission) {
		if (this.missionRepo.existsById(mission.getId())) {
			this.missionRepo.delete(mission);

		} else {
			throw new InvalidIdException();
		}

	}

	/**
	 * Verifie que la mission exist par l'id @param .getId si la mission existe
	 * il alors je set tous les parmaetre de @param et je sauvegarde la mission
	 * en base
	 * 
	 * @param missionAModifier
	 * @return void
	 */
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

	/**
	 * Ont prend en parametre 2 dates Ont itere sur les 2 dates tans que la
	 * premiere ce trouve avant la deuxieme Ont verifie chaque jour, que la
	 * dates n'est pas egal a un dimanche ou un samedi
	 * 
	 * @param dateDebut
	 * @param dateFin
	 * @return count
	 */
	public static int getNbJoursTravailler(LocalDate dateDebut, LocalDate dateFin) {
		int count = 0;
		LocalDate current = dateDebut;
		while (current.isBefore(dateFin.plusDays(1))) {
			if (!current.getDayOfWeek().equals(DayOfWeek.SATURDAY)
					&& !current.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				count++;
			}
			current = current.plusDays(1);

		}
		return count;

	}

	/**
	 * Cette methode s'execute toutes les nuits à 1h du matin
	 * 
	 * Ont recupere la liste de toute les missions Ont la filtre par Statut =>
	 * INITIALE Sur chaques elements de la liste ont set le statut a EN ATTENTE
	 * DE VALIDATION
	 * 
	 * Ont calcul la prime 1- ont calcul le nombre de jour entre les deux date
	 * travaille avec la methode "getNbJoursTravailler" 2-ont verifie que la
	 * nature de missions na pas etais modifié entre le debut et fin de mission
	 * 3-si modifier ont passe dans la seconde condition qui elle recupere le
	 * pourcentage de l'ancienne nature 4- ont calcul la prime Prime = (nombre
	 * de jours travaillés)* TJM * %Prime/100 5- ont cette le resultat dans la
	 * mission assoccié et ont recommence pour chaque mission de la liste
	 * 
	 * @return void
	 */
	@Scheduled(cron = "1 * * * * *")
	public void TraitementDeNuit() {
		// passage des missions du statut initiale au status en attente de
		// confirmation
		List<Mission> missions = listerMission().stream().filter(mission -> mission.getStatut().equals(Statut.INITIALE))
				.collect(Collectors.toList());

		missions.forEach(element -> {
			element.setStatut(Statut.EN_ATTENTE_VALIDATION);
			this.missionRepo.save(element);

			// Calcul de la prime
		});
		List<Mission> missionEchu = listerMission().stream()
				.filter(missionDate -> missionDate.getDateFin().isBefore(LocalDate.now())).collect(Collectors.toList());

		missionEchu.forEach(mission -> {
			int nbJourTravaille = getNbJoursTravailler(mission.getDateDebut(), mission.getDateFin());
			if (mission.getNatureMission().getDateFin() == null) {

				mission.setPrime(nbJourTravaille
						* ((mission.getNatureMission().getPourcentage() * mission.getNatureMission().getTjm()) / 100));

				this.missionRepo.save(mission);

			} else if ((mission.getNatureMission().getDateFin() != null)
					&& (mission.getDateDebut().isBefore(mission.getNatureMission().getDateFin()))) {

				mission.setPrime(nbJourTravaille
						* ((mission.getNatureMission().getPourcentage() * mission.getNatureMission().getTjm()) / 100));

				this.missionRepo.save(mission);
			}

		});
		System.out.println("cron success" + " " + LocalTime.now());
	}
}
