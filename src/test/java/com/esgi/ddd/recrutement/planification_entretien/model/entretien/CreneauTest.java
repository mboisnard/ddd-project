package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.assertEquals;

public class CreneauTest {

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

    private final Date NOT_COLLIDING_START_DATE = Date.from(
            LocalDateTime.of(
                    LocalDate.of(1994, DECEMBER, 24),
                    LocalTime.of(17, 20)
            ).toInstant(UTC)
    );

    private final Duration MOCK_DURATION = Duration.ofHours(1L);


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCreneauWhenStartDateIsNull() {
        new Creneau(null, MOCK_DURATION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCreneauWhenDurationIsNull() {
        new Creneau(MOCK_START_DATE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCreneauWhenDurationIsNegative() {
        Duration duration = Duration.ofHours(-1L);
        new Creneau(MOCK_START_DATE, duration);
    }

    @Test
    public void shouldCreateCreneau() {
        Creneau creneau = new Creneau(MOCK_START_DATE, MOCK_DURATION);

        assertEquals(creneau.getStartDate(), MOCK_START_DATE);
        assertEquals(creneau.getEndDate(), MOCK_END_DATE);
    }

    @Test
    public void shouldCollideWithCreneau() {
        Creneau creneau = new Creneau(MOCK_START_DATE, MOCK_DURATION);
        Creneau collidingCreneau = new Creneau(MOCK_START_DATE, MOCK_DURATION);

        boolean collides = creneau.collidesWith(collidingCreneau);

        assertEquals(true, collides);
    }

    @Test
    public void shouldNotCollideWithCreneau() {
        Creneau creneau = new Creneau(MOCK_START_DATE, MOCK_DURATION);
        Creneau notCollidingCreneau = new Creneau(NOT_COLLIDING_START_DATE, MOCK_DURATION);

        boolean collides = creneau.collidesWith(notCollidingCreneau);

        assertEquals(false, collides);
    }
}
