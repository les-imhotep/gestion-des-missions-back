package dev.services;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import dev.entities.Collegue;
import dev.entities.Mission;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.exceptions.InvalidDateMissionsException;
import dev.exceptions.InvalidDateTransportMissionException;
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

	public List<Mission> listerMission() {
		return this.missionRepo.findAll();

	}

	public String getUserDetails() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public List<Mission> findAllMission() {

		return this.missionRepo.findAllByCollegueEmail(getUserDetails());

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
			throw new InvalidDateMissionsException();
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
	public List<?> findAllPrimes() {
		// recupérer les primes de chaque mission pour un utilisateur
		return findAllMission().stream().map(mission -> mission.getPrime()).collect(Collectors.toList());

	}

	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

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
			//
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

			// Data
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
}
