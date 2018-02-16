package com.esgi.ddd.recrutement.planification_entretien.application;

import com.esgi.ddd.recrutement.core.infrastructure.LongIdentityProvider;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.candidat.CandidatRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.candidat.InMemoryCandidatRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur.ConsultantRecruteurRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur.InMemoryConsultantRecruteurRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.InMemoryEntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteurId;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauAdapter;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauAdapterImpl;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauDTO;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import com.esgi.ddd.recrutement.sourcing.application.RechercherProfil;
import com.esgi.ddd.recrutement.sourcing.application.RechercherProfilImpl;
import com.esgi.ddd.recrutement.sourcing.application.SelectionnerCandidat;
import com.esgi.ddd.recrutement.sourcing.application.SelectionnerCandidatImpl;
import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.InMemoryProspectRepository;
import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.ProspectRepository;
import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;
import com.esgi.ddd.recrutement.sourcing.model.prospect.ProspectId;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien.State.PENDING;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

public class PlanifierEntretienIntegrationTest {
	
	private SelectionnerCandidat selectionnerCandidat;
	private PlanifierEntretien planifierEntretien;
	
	private Prospect expectedProspect;
	private ConsultantRecruteur expectedConsultantRecruteur;
	
	@Before
	public void setUp() {
		final EntretienRepository entretienRepository = new InMemoryEntretienRepository();
		final ProspectRepository prospectRepository = new InMemoryProspectRepository();
		final ConsultantRecruteurRepository consultantRecruteurRepository = new InMemoryConsultantRecruteurRepository();
		
		final RechercherProfil rechercherProfil = new RechercherProfilImpl(prospectRepository);
		selectionnerCandidat = new SelectionnerCandidatImpl(rechercherProfil);
		
		final CandidatRepository candidatRepository = new InMemoryCandidatRepository(prospectRepository);
		final CreneauAdapter creneauAdapter = new CreneauAdapterImpl();
		final ChercherConsultantRecruteur chercherConsultantRecruteur = new ChercherConsultantRecruteurImpl(consultantRecruteurRepository, entretienRepository);
		final IdentityProvider<Long> identityProvider = new LongIdentityProvider();
		planifierEntretien = new PlanifierEntretienImpl(creneauAdapter, chercherConsultantRecruteur, identityProvider, candidatRepository, entretienRepository);
		
		expectedProspect = new Prospect(new ProspectId(1L), "Julien", "Bertauld", "Java", 1.);
		final Prospect mockProspectJava = new Prospect(new ProspectId(2L), "Vincent", "Rossignol", "Java", 1.);
		final Prospect mockProspectCSharp = new Prospect(new ProspectId(3L), "Vincent", "Milano", "C#", 1.);
		
		expectedConsultantRecruteur = new ConsultantRecruteur(new ConsultantRecruteurId(1L), "Mathieu", "Boisnard", new Profil("Java", 1.));
		final ConsultantRecruteur consultantRecruteurCSharp = new ConsultantRecruteur(new ConsultantRecruteurId(2L), "Valentin", "Fries", new Profil("C#", 1.));
		
		prospectRepository.save(expectedProspect);
		prospectRepository.save(mockProspectJava);
		prospectRepository.save(mockProspectCSharp);
		
		consultantRecruteurRepository.save(expectedConsultantRecruteur);
		consultantRecruteurRepository.save(consultantRecruteurCSharp);
	}
	
	@Test
	public void shouldPlanifierUnEntretienForJulienBertauldWithMathieuBoisnardWhenSearchingANewJavaCandidat() {
		final CreneauDTO creneau = CreneauDTO.builder()
			.startDate(Date.from(LocalDateTime.of(2018, 1, 1, 14, 0).toInstant(UTC)))
			.duration(Duration.ofHours(1L))
			.build();
		
		final Optional<Prospect> prospect = selectionnerCandidat.selectionnerCandidat("Java");
		assertThat(prospect).contains(expectedProspect);
		
		//noinspection ConstantConditions
		final Long candidatId = prospect.get().getId().getId();
		final Entretien entretien = planifierEntretien.planifierEntretien(candidatId, creneau);
		
		assertThat(entretien).isNotNull();
		
		assertThat(entretien.getCandidat()).hasFieldOrPropertyWithValue("firstname", expectedProspect.getFirstname());
		assertThat(entretien.getCandidat()).hasFieldOrPropertyWithValue("lastname", expectedProspect.getLastname());
		assertThat(entretien.getCandidat().getProfil()).hasFieldOrPropertyWithValue("skill", expectedProspect.getSkill());
		assertThat(entretien.getCandidat().getProfil()).hasFieldOrPropertyWithValue("experience", expectedProspect.getExperience());
		
		assertThat(entretien.getConsultantRecruteur()).hasFieldOrPropertyWithValue("firstname", expectedConsultantRecruteur.getFirstname());
		assertThat(entretien.getConsultantRecruteur()).hasFieldOrPropertyWithValue("lastname", expectedConsultantRecruteur.getLastname());
		assertThat(entretien.getConsultantRecruteur().getProfil()).hasFieldOrPropertyWithValue("skill", expectedConsultantRecruteur.getProfil().getSkill());
		assertThat(entretien.getConsultantRecruteur().getProfil()).hasFieldOrPropertyWithValue("experience", expectedConsultantRecruteur.getProfil().getExperience());
		
		assertThat(entretien.getCreneau()).hasFieldOrPropertyWithValue("startDate", creneau.getStartDate());
		assertThat(entretien.getCreneau()).hasFieldOrPropertyWithValue("endDate", Date.from(creneau.getStartDate().toInstant().plus(creneau.getDuration())));
		
		assertThat(entretien.getState()).isEqualTo(PENDING);
	}
}