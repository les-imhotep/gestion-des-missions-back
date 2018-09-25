package dev.services;

import java.util.List;

import dev.entities.Mission;
import dev.repositories.MissionRepo;

public class MissionService {
	private MissionRepo missionRepo;

	public List<Mission> listerMission() {
		return this.missionRepo.findAll();
	}
}
