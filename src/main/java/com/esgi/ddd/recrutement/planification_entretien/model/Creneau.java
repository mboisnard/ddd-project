package com.esgi.ddd.recrutement.planification_entretien.model;

import com.esgi.ddd.recrutement.core.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Creneau extends ValueObject {
	
	private final Date startDate;
	private final Date endDate;
	
	public Creneau(final Date startDate, final Duration duration) {
		if(startDate == null)
			throw new IllegalArgumentException("startDate cannot be null");
		if(duration == null || duration.getSeconds() < 0)
			throw new IllegalArgumentException("duration cannot be null or negative");
		
		final Instant endInstant = startDate.toInstant().plus(duration);
		
		this.startDate = startDate;
		this.endDate = Date.from(endInstant);
	}
	
	public boolean collidesWith(final Creneau creneau) {
		return creneau.getStartDate().getTime() <= this.endDate.getTime()
			&& this.startDate.getTime() <= creneau.getEndDate().getTime();
	}
}
