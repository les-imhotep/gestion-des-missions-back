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
		return absenceDto;
	};

	Converter<AbsenceDto, Absence> ABSENCE_DTO_TO_ABSENCE = source -> {
		Absence absence = new Absence();

		return absence;
	};

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

		return natureMissionDto;
	};

	Converter<NatureMissionDto, NatureMission> NATUREMISSION_DTO_TO_NATUREMISSION = source -> {
		NatureMission natureMission = new NatureMission();

		return natureMission;
	};
	// Converter for noteDeFrais
	Converter<NoteDeFrais, NoteDeFraisDto> NOTEDEFRAIS_TO_NOTEDEFRAIS_DTO = source -> {
		NoteDeFraisDto noteDeFraisDto = new NoteDeFraisDto();
		return noteDeFraisDto;
	};

	Converter<NoteDeFraisDto, NoteDeFrais> NOTEDEFRAIS_DTO_TO_NOTEDEFRAIS = source -> {
		NoteDeFrais noteDeFrais = new NoteDeFrais();
		return noteDeFrais;
	};

	// Converter for prime
	Converter<Prime, PrimeDto> PRIME_TO_PRIME_DTO = source -> {
		PrimeDto primeDto = new PrimeDto();
		return primeDto;
	};

	Converter<PrimeDto, Prime> PRIME_DTO_TO_PRIME = source -> {
		Prime prime = new Prime();
		return prime;
	};
}
