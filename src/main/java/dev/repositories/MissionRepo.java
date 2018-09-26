package dev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Mission;

public interface MissionRepo extends JpaRepository<Mission, Long> {
	List<Mission> findAllByCollegueEmail(String username);
}
