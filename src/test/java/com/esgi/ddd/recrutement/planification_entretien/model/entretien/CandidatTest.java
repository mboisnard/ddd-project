package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CandidatTest {

    private final String MOCK_FIRSTNAME = "John";
    private final String MOCK_LASTNAME = "Doe";
    private final Profil MOCK_PROFIL = new Profil("Java", 1.);

    private final String OTHER_FIRSTNAME = "Matthew";
    private final String OTHER_LASTNAME = "McDough";
    private final Profil OTHER_PROFIL = new Profil("C#", 3.);

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstnameIsNullThenCandidatShouldNotBeCreated() {
        new Candidat(null, MOCK_LASTNAME, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstnameIsEmptyThenCandidatShouldNotBeCreated() {
        new Candidat("", MOCK_LASTNAME, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLastnameIsNullThenCandidatShouldNotBeCreated() {
        new Candidat(MOCK_FIRSTNAME, null, MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLastnameIsEmptyThenCandidatShouldNotBeCreated() {
        new Candidat(MOCK_FIRSTNAME, "", MOCK_PROFIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenProfilIsNullThenCandidatShouldNotBeCreated() {
        new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, null);
    }

    @Test
    public void whenAttributesAreValidThenCandidatShouldBeCreated() {
        final Candidat candidat = new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);

        assertEquals(MOCK_FIRSTNAME, candidat.getFirstname());
        assertEquals(MOCK_LASTNAME, candidat.getLastname());
        assertTrue(candidat.getProfil().compareTo(MOCK_PROFIL) == 0);
    }

    @Test
    public void whenComparingCandidatWithSameAttributesThenItShouldReturnTrue() {
        final Candidat first = new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);
        final Candidat second = new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);

        assertEquals(0, first.compareTo(second));
    }

    @Test
    public void whenComparingCandidatWithAtLeastOneDifferentAttributeThenItShouldReturnFalse() {
        final Candidat origin = new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);

        final Candidat differentFirstname = new Candidat(OTHER_FIRSTNAME, MOCK_LASTNAME, MOCK_PROFIL);
        final Candidat differentLastname = new Candidat(MOCK_FIRSTNAME, OTHER_LASTNAME, MOCK_PROFIL);
        final Candidat differentProfil = new Candidat(MOCK_FIRSTNAME, MOCK_LASTNAME, OTHER_PROFIL);

        assertTrue(origin.compareTo(differentFirstname) != 0);
        assertTrue(origin.compareTo(differentLastname) != 0);
        assertTrue(origin.compareTo(differentProfil) != 0);
    }
}
