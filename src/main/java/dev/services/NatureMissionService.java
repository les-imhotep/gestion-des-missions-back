package dev.services;

import java.util.List;

import dev.entities.NatureMission;
import dev.repositories.NatureMissionRepo;

public class NatureMissionService {
	NatureMissionRepo natureMissionRepo;

	public List<NatureMission> findAllNatureMission() {
		return natureMissionRepo.findAll();

	}
}
