package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Mission;

public interface MissionRepo extends JpaRepository<Mission, Long> {

}
