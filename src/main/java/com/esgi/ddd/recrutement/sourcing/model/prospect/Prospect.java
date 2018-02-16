package com.esgi.ddd.recrutement.sourcing.model.prospect;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.ProspectRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Prospect extends Entity<ProspectId> {
	
	private final String firstname;
	private final String lastname;
	
	private final String skill;
	private final Double experience;
	
	public Prospect(final ProspectId id, final String firstname, final String lastname, final String skill, final Double experience) {
		super(id);
		
		if(firstname == null || firstname.isEmpty())
			throw new IllegalArgumentException("firstname cannot be null or empty");
		if(lastname == null || lastname.isEmpty())
			throw new IllegalArgumentException("lastname cannot be null or empty");
		if(skill == null || skill.isEmpty())
			throw new IllegalArgumentException("skill cannot be null or empty");
		if(experience == null || experience < 0.)
			throw new IllegalArgumentException("experience cannot be null or negative");
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.skill = skill;
		this.experience = experience;
	}
	
	public Prospect enregistrer(final ProspectRepository prospectRepository) {
		return prospectRepository.save(this);
	}
}
