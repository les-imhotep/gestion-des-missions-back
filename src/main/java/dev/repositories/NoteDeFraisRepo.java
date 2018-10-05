package dev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Mission;
import dev.entities.NoteDeFrais;

public interface NoteDeFraisRepo extends JpaRepository<NoteDeFrais, Long> {

	List<NoteDeFrais> findAllByMissionCollegueEmail(String userDetails);

	NoteDeFrais findNoteDeFraisByMission(Mission mission);

}
