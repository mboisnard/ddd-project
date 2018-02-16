package com.esgi.ddd.recrutement.planification_entretien.model.profil;

import com.esgi.ddd.recrutement.core.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Profil extends ValueObject {
	
	private final String skill;
	private final Double experience;
	
	public Profil(final String skill, final Double experience) {
		if (skill == null || skill.isEmpty())
			throw new IllegalArgumentException("skill cannot be null or empty");
		if (experience == null || experience < 0.)
			throw new IllegalArgumentException("experience cannot be null or negative");
		
		this.skill = skill;
		this.experience = experience;
	}
}
