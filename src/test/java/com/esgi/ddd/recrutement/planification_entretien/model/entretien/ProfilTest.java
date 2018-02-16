package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilTest {

    private final Double MOCK_EXPERIENCE = 1.;
    private final Double OTHER_EXPERIENCE = 7.;

    private final String JAVA_SKILL = "Java";
    private final String OTHER_SKILL = "Rust";

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenSkillIsNull() {
        new Profil(null, MOCK_EXPERIENCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenSkillIsEmpty() {
        new Profil("", MOCK_EXPERIENCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenExperienceIsNull() {
        new Profil(JAVA_SKILL, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenExperienceIsNegative() {
        new Profil(JAVA_SKILL, -1.);
    }

    @Test
    public void shouldCreateProfil() {
        final Profil profil = new Profil(JAVA_SKILL, MOCK_EXPERIENCE);

        assertEquals(JAVA_SKILL, profil.getSkill());
        assertEquals(MOCK_EXPERIENCE, profil.getExperience());
    }

    @Test
    public void shouldCompareIdentical() {
        final Profil first = new Profil(JAVA_SKILL, MOCK_EXPERIENCE);
        final Profil second = new Profil(JAVA_SKILL, MOCK_EXPERIENCE);

        assertEquals(0, first.compareTo(second));
    }

    @Test
    public void shouldCompareNotIdentical() {
        final Profil origin = new Profil(JAVA_SKILL, MOCK_EXPERIENCE);

        final Profil differentSkill = new Profil(OTHER_SKILL, MOCK_EXPERIENCE);
        final Profil differentExperience = new Profil(JAVA_SKILL, OTHER_EXPERIENCE);

        assertTrue(origin.compareTo(differentSkill) != 0);
        assertTrue(origin.compareTo(differentExperience) != 0);
    }
}
