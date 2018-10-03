package dev;

import org.springframework.core.convert.converter.Converter;

import dev.controller.dto.AbsenceDto;
import dev.controller.dto.LigneDeFraisDto;
import dev.controller.dto.MissionDto;
import dev.controller.dto.NatureMissionDto;
import dev.controller.dto.NoteDeFraisDto;
import dev.entities.Absence;
import dev.entities.LigneDeFrais;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.NoteDeFrais;

public interface Converters {
	// Converter for absence

	Converter<Absence, AbsenceDto> ABSENCE_TO_ABSENCE_DTO = absence -> {
		AbsenceDto absenceDto = new AbsenceDto(absence);
		return absenceDto;
	};

	Converter<AbsenceDto, Absence> ABSENCE_DTO_TO_ABSENCE = absenceDto -> {
		Absence absence = new Absence(absenceDto);

		return absence;
	};

	// Converter for mission
	Converter<Mission, MissionDto> MISSION_TO_MISSION_DTO = mission -> {
		MissionDto missionDto = new MissionDto(mission);
		return missionDto;
	};

	Converter<MissionDto, Mission> MISSION_DTO_TO_MISSION = missionDto -> {
		Mission mission = new Mission(missionDto);

		return mission;
	};

	// Converter for natureMission

	Converter<NatureMission, NatureMissionDto> NATUREMISSION_TO_NATUREMISSION_DTO = natureMission -> {
		NatureMissionDto natureMissionDto = new NatureMissionDto(natureMission);

		return natureMissionDto;
	};

	Converter<NatureMissionDto, NatureMission> NATUREMISSION_DTO_TO_NATUREMISSION = natureMissionDto -> {
		NatureMission natureMission = new NatureMission(natureMissionDto);

		return natureMission;
	};
	// Converter for noteDeFrais
	Converter<LigneDeFrais, LigneDeFraisDto> LIGNEDEFRAIS_TO_LIGNEDEFRAIS_DTO = ligneDeFrais -> {
		LigneDeFraisDto ligneDeFraisDto = new LigneDeFraisDto(ligneDeFrais);
		return ligneDeFraisDto;
	};

	Converter<LigneDeFraisDto, LigneDeFrais> LIGNEDEFRAIS_DTO_TO_LIGNEDEFRAIS = ligneDeFraisDto -> {
		LigneDeFrais ligneDeFrais = new LigneDeFrais(ligneDeFraisDto);
		return ligneDeFrais;
	};
	// Converter for ligneDeFrais

	Converter<NoteDeFrais, NoteDeFraisDto> NOTEDEFRAIS_TO_NOTEDEFRAIS_DTO = noteDeFrais -> {
		NoteDeFraisDto noteDeFraisDto = new NoteDeFraisDto(noteDeFrais);
		return noteDeFraisDto;
	};

	Converter<NoteDeFraisDto, NoteDeFrais> NOTEDEFRAIS_DTO_TO_NOTEDEFRAIS = noteDeFraisDto -> {
		NoteDeFrais noteDeFrais = new NoteDeFrais(noteDeFraisDto);
		return noteDeFrais;

	};
	// Converter for prime

}
