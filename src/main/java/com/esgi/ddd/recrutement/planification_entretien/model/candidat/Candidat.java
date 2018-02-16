package com.esgi.ddd.recrutement.planification_entretien.model.candidat;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.core.model.ValueObject;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Candidat extends Entity<CandidatId> {
	
	private final String firstname;
	private final String lastname;
	
	private final Profil profil;
	
	public Candidat(final CandidatId id, final String firstname, final String lastname, final Profil profil) {
		super(id);
		
		if (firstname == null || firstname.isEmpty())
			throw new IllegalArgumentException("firstname cannot be null or empty");
		if (lastname == null || lastname.isEmpty())
			throw new IllegalArgumentException("lastname cannot be null or empty");
		if (profil == null)
			throw new IllegalArgumentException("profil cannot be null");
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.profil = profil;
	}
}
