package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.infrastructure.LongIdentityProvider;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.planification_entretien.application.ChercherConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.CandidatId;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteurId;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.Creneau;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsultantRecruteurTest {

    private final CandidatId MOCK_CANDIDAT_ID = new CandidatId(1L);
    
    private final ConsultantRecruteurId MOCK_ID = new ConsultantRecruteurId(1L);
    private final String MOCK_FIRSTNAME = "John";
    private final String MOCK_LASTNAME = "Doe";
    private final Profil MOCK_PROFIL = new Profil("Java", 1.);
    private final ConsultantRecruteur MOCK_CONSULTANT_RECRUTEUR = new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);
    
    private final String OTHER_FIRSTNAME = "Matthew";
    private final String OTHER_LASTNAME = "McDough";
    private final Profil OTHER_PROFIL = new Profil("C#", 3.);

    private final Date MOCK_START_DATE = Date.from(
        LocalDateTime.of(
            LocalDate.of(1993, AUGUST, 4),
            LocalTime.of(0, 25)
        ).toInstant(UTC)
    );

    private final Date MOCK_END_DATE = Date.from(
        LocalDateTime.of(
            LocalDate.of(1993, AUGUST, 4),
            LocalTime.of(1, 25)
        ).toInstant(UTC)
    );

    private final Date DISPONIBLE_START_DATE = Date.from(
        LocalDateTime.of(
            LocalDate.of(1994, DECEMBER, 24),
            LocalTime.of(17, 20)
        ).toInstant(UTC)
    );

    private final Duration MOCK_DURATION = Duration.ofHours(1L);

    private final Candidat MOCK_CANDIDAT = new Candidat(new CandidatId(2L), MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);
    private final Creneau MOCK_CRENEAU_CANDIDAT = new Creneau(MOCK_START_DATE, MOCK_DURATION);
    private final Creneau MOCK_CRENEAU_DISPONIBLE = new Creneau(DISPONIBLE_START_DATE, MOCK_DURATION);

    private class MockChercherConsultantRecruteur implements ChercherConsultantRecruteur {
        @Override
        public Optional<ConsultantRecruteur> chercherConsultantRecruteur(final Candidat candidat, final Creneau creneau) {
            return Optional.of(MOCK_CONSULTANT_RECRUTEUR);
        }
    }

    private MockChercherConsultantRecruteur MOCK_CHERCHER_CONSULTANT_RECRUTEUR = new MockChercherConsultantRecruteur();

    private class MockEntretienRepository implements EntretienRepository {
        @Override
        public List<Entretien> getAllByConsultantRecruteur(ConsultantRecruteur consultantRecruteur) {
            final IdentityProvider<Long> longIdentityProvider = new LongIdentityProvider();

            final Entretien entretien = new Entretien(longIdentityProvider, MOCK_CANDIDAT, MOCK_CRENEAU_CANDIDAT, MOCK_CHERCHER_CONSULTANT_RECRUTEUR);
            return new ArrayList<>(Arrays.asList(entretien));
        }

        @Override
        public Entretien save(Entretien entretien) {
            return null;
        }
    }

    private MockEntretienRepository MOCK_ENTRETIEN_REPOSITORY = new MockEntretienRepository();

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstnameIsNullThenConsultantRecruteurShouldNotBeCreated() {
        new ConsultantRecruteur(MOCK_ID, null, MOCK_LASTNAME, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstnameIsEmptyThenConsultantRecruteurShouldNotBeCreated() {
        new ConsultantRecruteur(MOCK_ID, "", MOCK_LASTNAME, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLastnameIsNullThenConsultantRecruteurShouldNotBeCreated() {
        new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, null, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLastnameIsEmptyThenConsultantRecruteurShouldNotBeCreated() {
        new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, "", MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenProfilIsNullThenConsultantRecruteurShouldNotBeCreated() {
        new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, null);
    }

    @Test
    public void whenAttributesAreValidThenConsultantRecruteurShouldBeCreated() {
        assertEquals(MOCK_FIRSTNAME, MOCK_CONSULTANT_RECRUTEUR.getFirstname());
        assertEquals(MOCK_LASTNAME, MOCK_CONSULTANT_RECRUTEUR.getLastname());
        assertTrue(MOCK_CONSULTANT_RECRUTEUR.getProfil().compareTo(MOCK_PROFIL) == 0);
    }

    @Test
    public void whenComparingConsultantRecruteurWithSameAttributesThenItShouldReturnTrue() {
        final ConsultantRecruteur second = new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);

        assertEquals(true, MOCK_CONSULTANT_RECRUTEUR.equals(second));
    }

    @Test
    public void whenComparingConsultantRecruteurWithAtLeastOneDifferentAttributeThenItShouldReturnFalse() {
        final ConsultantRecruteur differentFirstname = new ConsultantRecruteur(MOCK_ID, OTHER_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);
        final ConsultantRecruteur differentLastname = new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, OTHER_LASTNAME, MOCK_PROFIL);
        final ConsultantRecruteur differentProfil = new ConsultantRecruteur(MOCK_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, OTHER_PROFIL);

        assertEquals(false, MOCK_CONSULTANT_RECRUTEUR.equals(differentFirstname));
        assertEquals(false, MOCK_CONSULTANT_RECRUTEUR.equals(differentLastname));
        assertEquals(false, MOCK_CONSULTANT_RECRUTEUR.equals(differentProfil));
    }
    
    @Test
    public void whenConsultantRecruteurHasDifferentProfilWithCandidatThenPeutTesterShouldBeFalse() {
        final Candidat candidat = new Candidat(MOCK_CANDIDAT_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, OTHER_PROFIL);

        assertEquals(false, MOCK_CONSULTANT_RECRUTEUR.peutTester(candidat));
    }

    @Test
    public void whenConsultantRecruteurHasSameProfilWithCandidatThenPeutTesterShouldBeTrue() {
        final Candidat candidat = new Candidat(MOCK_CANDIDAT_ID, MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);

        assertEquals(true, MOCK_CONSULTANT_RECRUTEUR.peutTester(candidat));
    }

    @Test
    public void whenConsultantRecruteurAlreadyHasAnEntretienOnGivenCreneauThenEstDisponibleShouldBeFalse() {
        assertEquals(false, MOCK_CONSULTANT_RECRUTEUR.estDisponible(MOCK_ENTRETIEN_REPOSITORY, MOCK_CRENEAU_CANDIDAT));
    }

    @Test
    public void whenConsultantRecruteurDoesNotHaveEntretienOnGivenCreneauThenEstDisponibleShouldBeTrue() {
        assertEquals(true, MOCK_CONSULTANT_RECRUTEUR.estDisponible(MOCK_ENTRETIEN_REPOSITORY, MOCK_CRENEAU_DISPONIBLE));
    }
}
