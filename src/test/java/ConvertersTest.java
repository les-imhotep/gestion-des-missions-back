import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import dev.Converters;
import dev.controller.dto.MissionDto;
import dev.controller.dto.NatureMissionDto;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.enumerations.Facturation;
import dev.entities.enumerations.Statut;

public class ConvertersTest {

	@Test
	public void missionTest() {

		Mission mission = new Mission();
		mission.setPrime(10);
		mission.setDateDebut(LocalDate.of(2005, 5, 31));
		mission.setDateFin(LocalDate.of(3999, 6, 15));
		mission.setNatureMission(null);
		mission.setStatut(Statut.VALIDEE);
		mission.setVilleArrivee("Paris");
		mission.setVilleDepart("Nantes");

		MissionDto missionDto = new MissionDto();
		missionDto.setPrime(10);
		missionDto.setDateDebut("2005-05-31");
		missionDto.setDateFin("3999-06-15");
		missionDto.setNatureMission(null);
		missionDto.setStatut(Statut.VALIDEE);
		missionDto.setVilleArrivee("Paris");
		missionDto.setVilleDepart("Nantes");

		// converter mission dto to mission
		assertTrue(Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getPrime() == mission.getPrime());
		assertTrue(Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getVilleArrivee()
				.equals(mission.getVilleArrivee()));
		assertTrue(Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getVilleDepart()
				.equals(mission.getVilleDepart()));
		assertTrue(
				Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getDateDebut().isEqual(mission.getDateDebut()));
		assertTrue(Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getDateFin().isEqual(mission.getDateFin()));
		assertTrue(
				Converters.MISSION_DTO_TO_MISSION.convert(missionDto).getNatureMission() == mission.getNatureMission());

		// converter mission to mission dto
		assertTrue(Converters.MISSION_TO_MISSION_DTO.convert(mission).getPrime() == missionDto.getPrime());
		assertTrue(Converters.MISSION_TO_MISSION_DTO.convert(mission).getVilleArrivee()
				.equals(missionDto.getVilleArrivee()));
		assertTrue(Converters.MISSION_TO_MISSION_DTO.convert(mission).getVilleDepart()
				.equals(missionDto.getVilleDepart()));
		assertTrue(Converters.MISSION_TO_MISSION_DTO.convert(mission).getDateDebut().equals(missionDto.getDateDebut()));
		assertTrue(Converters.MISSION_TO_MISSION_DTO.convert(mission).getDateFin().equals(missionDto.getDateFin()));
		assertTrue(
				Converters.MISSION_TO_MISSION_DTO.convert(mission).getNatureMission() == missionDto.getNatureMission());

	}

	@Test
	public void natureMissionTest() {

		NatureMission formation = new NatureMission();
		formation.setPrime(true);
		formation.setFacturation(Facturation.NON_FACTUREE);
		formation.setName("Jean");
		formation.setPourcentage(10);
		formation.setTjm(150);
		formation.setDateFin(LocalDate.of(2999, 12, 31));

		NatureMissionDto formationDto = new NatureMissionDto();
		formationDto.setPrime(true);
		formationDto.setFacturation(Facturation.NON_FACTUREE);
		formationDto.setName("Jean");
		formationDto.setPourcentage(10);
		formationDto.setTjm(150);
		formationDto.setDateFin(LocalDate.of(2999, 12, 31));

		// converter naturemission dto to naturemission
		assertTrue(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).getFacturation() == formation
				.getFacturation());

		assertTrue(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).getDateFin()
				.isEqual(formation.getDateFin()));
		assertTrue(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).getName()
				.equals(formation.getName()));
		assertTrue(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).getPourcentage() == formation
				.getPourcentage());
		assertTrue(Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).getTjm() == formation.getTjm());

		assertTrue(
				Converters.NATUREMISSION_DTO_TO_NATUREMISSION.convert(formationDto).isPrime() == formation.isPrime());
		// converter naturemission to naturemission dto
		assertTrue(Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).getFacturation() == formationDto
				.getFacturation());

		assertTrue(Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).getDateFin()
				.isEqual(formationDto.getDateFin()));
		assertTrue(Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).getName()
				.equals(formationDto.getName()));
		assertTrue(Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).getPourcentage() == formationDto
				.getPourcentage());
		assertTrue(Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).getTjm() == formationDto.getTjm());

		assertTrue(
				Converters.NATUREMISSION_TO_NATUREMISSION_DTO.convert(formation).isPrime() == formationDto.isPrime());
	}

}
