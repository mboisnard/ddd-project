package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilTest {

    private final Double VALID_EXPERIENCE = 1.;

    private final String JAVA_SKILL = "Java";
    private final String RUST_SKILL = "Rust";

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenSkillIsNull() {
        new Profil(null, VALID_EXPERIENCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProfilWhenSkillIsEmpty() {
        new Profil("", VALID_EXPERIENCE);
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
        final Profil profil = new Profil(JAVA_SKILL, VALID_EXPERIENCE);

        assertEquals(JAVA_SKILL, profil.getSkill());
        assertEquals(VALID_EXPERIENCE, profil.getExperience());
    }

    @Test
    public void shouldCompareIdentical() {
        final Profil first = new Profil(JAVA_SKILL, VALID_EXPERIENCE);
        final Profil second = new Profil(JAVA_SKILL, VALID_EXPERIENCE);

        assertEquals(0, first.compareTo(second));
    }

    @Test
    public void shouldCompareNotIdentical() {
        final Profil first = new Profil(JAVA_SKILL, VALID_EXPERIENCE);
        final Profil second = new Profil(RUST_SKILL, VALID_EXPERIENCE);

        assertTrue(first.compareTo(second) != 0);
    }
}
