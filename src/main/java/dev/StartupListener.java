package dev;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;
import dev.entities.LigneDeFrais;
import dev.entities.Mission;
import dev.entities.NatureMission;
import dev.entities.Prime;
import dev.entities.RoleCollegue;
import dev.entities.Version;
import dev.entities.enumerations.Facturation;
import dev.entities.enumerations.Role;
import dev.entities.enumerations.Statut;
import dev.entities.enumerations.Transport;
import dev.repositories.CollegueRepo;
import dev.repositories.LigneDeFraisRepo;
import dev.repositories.MissionRepo;
import dev.repositories.NatureMissionRepo;
import dev.repositories.PrimeRepo;
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
	private LigneDeFraisRepo ligneDeFraisRepo;
	private PrimeRepo primeRepo;
	private NatureMissionRepo natureMissionRepo;
	private MissionRepo missionRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, MissionRepo missionRepo,
			NatureMissionRepo natureMissionRepo, LigneDeFraisRepo ligneDeFraisRepo, PrimeRepo primeRepo) {

		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.primeRepo = primeRepo;
		this.natureMissionRepo = natureMissionRepo;
		this.missionRepo = missionRepo;
		this.ligneDeFraisRepo = ligneDeFraisRepo;

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

		// Nature1 nature de mission fictive
		NatureMission conseil = new NatureMission();
		conseil.setPourcentage(3.5);
		conseil.setPrime(true);
		conseil.setTjm(750);
		conseil.setFacturation(Facturation.FACTUREE);
		conseil.setName("Conseil");
		this.natureMissionRepo.save(conseil);

		// Création de deux notes de frais
		LigneDeFrais ligneDeFrais = new LigneDeFrais();
		ligneDeFrais.setDateDebut(LocalDate.of(2018, 8, 17));
		ligneDeFrais.setDateFin(LocalDate.of(2018, 9, 30));
		ligneDeFrais.setNatureMission(conseil);
		ligneDeFrais.setVilleDepart("Nantes");
		ligneDeFrais.setVilleArrivee("Rennes");
		ligneDeFrais.setTransport(Transport.COVOITURAGE);
		this.ligneDeFraisRepo.save(ligneDeFrais);

		LigneDeFrais ligneDeFrais1 = new LigneDeFrais();
		ligneDeFrais1.setDateDebut(LocalDate.of(1018, 8, 17));
		ligneDeFrais1.setDateFin(LocalDate.of(1018, 9, 30));
		ligneDeFrais1.setNatureMission(conseil);
		ligneDeFrais1.setVilleDepart("Nantes");
		ligneDeFrais1.setVilleArrivee("Rennes");
		ligneDeFrais1.setTransport(Transport.COVOITURAGE);
		this.ligneDeFraisRepo.save(ligneDeFrais1);

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

		// primes
		Prime prime = new Prime();
		prime.setDateDebut(LocalDate.of(1999, 2, 15));
		prime.setDateFin(null);
		prime.setMontant(1000);
		prime.setNatureMission(conseil);
		primeRepo.save(prime);
	}

}
