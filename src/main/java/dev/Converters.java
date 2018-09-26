package dev;

import org.springframework.core.convert.converter.Converter;

import dev.controller.dto.AbsenceDto;
import dev.controller.dto.MissionDto;
import dev.controller.dto.NatureMissionDto;
import dev.controller.dto.NoteDeFraisDto;
import dev.controller.dto.PrimeDto;
import dev.entities.Absence;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.NoteDeFrais;
import dev.entities.Prime;

public interface Converters {
	// Converter for absence
	Converter<Absence, AbsenceDto> ABSENCE_TO_ABSENCE_DTO = source -> {
		AbsenceDto absenceDto = new AbsenceDto();

		absenceDto.setCollegue(source.getCollegue());
		absenceDto.setDateDebut(source.getDateDebut());
		absenceDto.setDateFin(source.getDateFin());

		return absenceDto;
	};

	Converter<AbsenceDto, Absence> ABSENCE_DTO_TO_ABSENCE = source -> {
		Absence absence = new Absence();

		absence.setCollegue(source.getCollegue());
		absence.setDateDebut(source.getDateDebut());
		absence.setDateFin(source.getDateFin());

		return absence;
	};
	// Collegue inutile

	// Converter for mission
	Converter<Mission, MissionDto> MISSION_TO_MISSION_DTO = mission -> {
		MissionDto missionDto = new MissionDto(mission);
		return missionDto;
	};

	Converter<MissionDto, Mission> MISSION_DTO_TO_MISSION = missionDto -> {
		Mission mission = new Mission();

		return mission;
	};

	// Converter for natureMission
	Converter<NatureMission, NatureMissionDto> NATUREMISSION_TO_NATUREMISSION_DTO = source -> {
		NatureMissionDto natureMissionDto = new NatureMissionDto();

		natureMissionDto.setFacturation(source.getFacturation());
		natureMissionDto.setPourcentage(source.getPourcentage());
		natureMissionDto.setPrime(source.isPrime());
		natureMissionDto.setTjm(source.getTjm());

		return natureMissionDto;
	};

	Converter<NatureMissionDto, NatureMission> NATUREMISSION_DTO_TO_NATUREMISSION = source -> {
		NatureMission natureMission = new NatureMission();

		natureMission.setFacturation(source.getFacturation());
		natureMission.setPourcentage(source.getPourcentage());
		natureMission.setPrime(source.isPrime());
		natureMission.setTjm(source.getTjm());

		return natureMission;
	};
	// Converter for noteDeFrais
	Converter<NoteDeFrais, NoteDeFraisDto> NOTEDEFRAIS_TO_NOTEDEFRAIS_DTO = source -> {
		NoteDeFraisDto noteDeFraisDto = new NoteDeFraisDto();

		noteDeFraisDto.setDateDebut(source.getDateDebut());
		noteDeFraisDto.setDateFin(source.getDateFin());
		noteDeFraisDto.setFrais(source.getFrais());
		noteDeFraisDto.setNatureMission(source.getNatureMission());
		noteDeFraisDto.setTransport(source.getTransport());
		noteDeFraisDto.setVilleArrivee(source.getVilleArrivee());
		noteDeFraisDto.setVilleDepart(source.getVilleDepart());

		return noteDeFraisDto;
	};

	Converter<NoteDeFraisDto, NoteDeFrais> NOTEDEFRAIS_DTO_TO_NOTEDEFRAIS = source -> {
		NoteDeFrais noteDeFrais = new NoteDeFrais();

		noteDeFrais.setDateDebut(source.getDateDebut());
		noteDeFrais.setDateFin(source.getDateFin());
		noteDeFrais.setFrais(source.getFrais());
		noteDeFrais.setNatureMission(source.getNatureMission());
		noteDeFrais.setTransport(source.getTransport());
		noteDeFrais.setVilleArrivee(source.getVilleArrivee());
		noteDeFrais.setVilleDepart(source.getVilleDepart());

		return noteDeFrais;
	};

	// Converter for prime
	Converter<Prime, PrimeDto> PRIME_TO_PRIME_DTO = source -> {
		PrimeDto primeDto = new PrimeDto();

		primeDto.setDateDebut(source.getDateDebut());
		primeDto.setDateFin(source.getDateFin());
		primeDto.setMontant(source.getMontant());
		primeDto.setNatureMission(source.getNatureMission());

		return primeDto;
	};

	Converter<PrimeDto, Prime> PRIME_DTO_TO_PRIME = source -> {
		Prime prime = new Prime();

		prime.setDateDebut(source.getDateDebut());
		prime.setDateFin(source.getDateFin());
		prime.setMontant(source.getMontant());
		prime.setNatureMission(source.getNatureMission());

		return prime;
	};
}
