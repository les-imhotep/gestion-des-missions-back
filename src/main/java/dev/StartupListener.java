package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.NoteDeFrais;
import dev.entities.RoleCollegue;
import dev.entities.Version;
import dev.entities.enumerations.Facturation;
import dev.entities.enumerations.Role;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.repositories.CollegueRepo;
import dev.repositories.MissionRepo;
import dev.repositories.NatureMissionRepo;
import dev.repositories.NoteDeFraisRepo;
import dev.repositories.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private NoteDeFraisRepo noteDeFraisRepo;
	private NatureMissionRepo natureMissionRepo;
	private MissionRepo missionRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo,
			NatureMissionRepo natureMissionRepo, NoteDeFraisRepo noteDeFraisRepo) {

		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.noteDeFraisRepo = noteDeFraisRepo;
		this.natureMissionRepo = natureMissionRepo;
		this.missionRepo = missionRepo;

	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("User");
		col3.setPrenom("DEV");
		col3.setEmail("manager@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_MANAGER)));
		this.collegueRepo.save(col3);

		NatureMission conseil = new NatureMission();
		conseil.setPourcentage(3.5);
		conseil.setPrime(true);
		conseil.setTjm(750);
		conseil.setFacturation(Facturation.FACTUREE);
		conseil.setName("Conseil");
		this.natureMissionRepo.save(conseil);

		NatureMission expertiseTechnique = new NatureMission();
		expertiseTechnique.setPourcentage(4);
		expertiseTechnique.setPrime(true);
		expertiseTechnique.setTjm(1000);
		expertiseTechnique.setFacturation(Facturation.FACTUREE);
		expertiseTechnique.setName("Expertise technique");
		this.natureMissionRepo.save(expertiseTechnique);

		NatureMission formation = new NatureMission();
		formation.setPrime(false);
		formation.setFacturation(Facturation.NON_FACTUREE);
		formation.setName("Formation");
		formation.setDateFin(LocalDate.of(2018, 9, 20));
		this.natureMissionRepo.save(formation);

		NatureMission formation1 = new NatureMission();
		formation1.setPrime(true);
		formation1.setFacturation(Facturation.NON_FACTUREE);
		formation1.setName("Formation");
		this.natureMissionRepo.save(formation1);

		// Création de deux notes de frais
		NoteDeFrais note1 = new NoteDeFrais();
		note1.setDateDebut(LocalDate.of(2018, 8, 17));
		note1.setDateFin(LocalDate.of(2018, 9, 30));
		note1.setNatureMission(formation);
		note1.setVilleDepart("Nantes");
		note1.setVilleArrivee("Rennes");
		note1.setTransport(Transport.COVOITURAGE);
		this.noteDeFraisRepo.save(note1);

		// ajout de misssions
		Mission mission = new Mission();
		mission.setCollegue(col1);
		mission.setDateDebut(LocalDate.now());
		mission.setDateFin(LocalDate.of(2019, 01, 21));
		mission.setNatureMission(null);
		mission.setPrime(5000);
		mission.setTransport(Transport.AVION);
		mission.setStatut(Statut.INITIALE);
		mission.setVilleArrivee("Paris");
		mission.setVilleDepart("Nantes");
		missionRepo.save(mission);

	}

}
