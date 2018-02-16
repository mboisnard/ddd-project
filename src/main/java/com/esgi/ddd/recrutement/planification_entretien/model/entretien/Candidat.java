package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Candidat extends ValueObject {
	
	private final String firstname;
	private final String lastname;
	
	private final Profil profil;
	
	Candidat(final String firstname, final String lastname, final Profil profil) {
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
