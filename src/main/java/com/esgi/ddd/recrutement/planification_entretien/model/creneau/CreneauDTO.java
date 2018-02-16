package com.esgi.ddd.recrutement.planification_entretien.model.creneau;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

@Data
@Builder
public class CreneauDTO implements Serializable {
	private final Date startDate;
	private final Duration duration;
}
