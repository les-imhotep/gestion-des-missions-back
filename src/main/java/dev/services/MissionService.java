package dev.services;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
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
import dev.exceptions.NoPrimeException;
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
	 * @return retourne que les mission du status renseigner
	 */
	public List<Mission> findMissionbyStatut(String statut) {

		return this.missionRepo.findAllByStatut(statut);

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

	// gestion des primes
	/**
	 * 
	 * @return une liste de mission pour un utilisateur
	 */
	public List<?> findAllPrimes() {
		// recupérer les primes de chaque mission pour un utilisateur
		return findAllMission().stream().map(mission -> mission.getPrime()).collect(Collectors.toList());

	}

	/**
	 * création d'un style pour les titres du pdf
	 * 
	 * @param workbook
	 * @return un style
	 */
	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	/**
	 * crée un flux de données avec la listes des primes
	 * 
	 * @return
	 * @throws IOException
	 */
	public HSSFWorkbook ddlPrime() throws IOException {

		List<Mission> list = findAllMission();
		if (list.isEmpty()) {
			throw new NoPrimeException();
		} else {

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Primes de " + list.get(0).getCollegue().getNom());

			int rownum = 0;
			Cell cell;
			Row row;

			HSSFCellStyle style = createStyleForTitle(workbook);
			style.setAlignment(HorizontalAlignment.CENTER);
			sheet.setColumnWidth((short) 0, (short) (15 * 256));
			sheet.setColumnWidth((short) 1, (short) (15 * 256));
			sheet.setColumnWidth((short) 2, (short) (25 * 256));
			sheet.setColumnWidth((short) 3, (short) (10 * 256));
			row = sheet.createRow(rownum);

			// Date Début
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Date Début");
			cell.setCellStyle(style);

			// Date Fin
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Date Fin");
			CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style);

			// Nature
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Nature");
			cell.setCellStyle(style);
			// Prime
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Prime");
			cell.setCellStyle(style);

			// remplir le tableau
			for (Mission mission : list) {
				rownum++;
				row = sheet.createRow(rownum);

				// Date Début (A)
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(mission.getDateDebut().toString());
				cell.setCellStyle(cellStyle);
				// Date Fin (B)
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(mission.getDateFin().toString());
				cell.setCellStyle(cellStyle);
				// Nature (C)
				cell = row.createCell(2, CellType.NUMERIC);
				cell.setCellValue(mission.getNatureMission().getName());
				cell.setCellStyle(cellStyle);
				// Prime (D)
				cell = row.createCell(3, CellType.NUMERIC);
				cell.setCellValue(mission.getPrime() + "€");
				cell.setCellStyle(cellStyle);

			}
			return workbook;
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

	/**
	 * vérifie si une mission est fini
	 * 
	 * @param mission
	 * @return true or false en fonction de la fin de la mission
	 */
	public boolean isMissionFini(Mission mission) {
		if (mission != null && mission.getDateFin() != null) {
			if (mission.getDateFin().isBefore(LocalDate.now())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * cherche une mission par id
	 * 
	 * @param id
	 * @return la mission trouvée si elle existe
	 */
	public Optional<Mission> findMissionById(Long id) {
		return this.missionRepo.findById(id);
	}

}
