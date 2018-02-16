package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.CandidatId;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteurId;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private class MockEntretienRepository implements EntretienRepository {
        @Override
        public List<Entretien> getAllByConsultantRecruteur(ConsultantRecruteur consultantRecruteur) {
            final Entretien entretien = new Entretien();
            return new ArrayList<>(Arrays.asList(entretien));
        }

        @Override
        public Entretien save(Entretien entretien) {
            return null;
        }
    }

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
}
