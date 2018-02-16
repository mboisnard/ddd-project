package com.esgi.ddd.recrutement.planification_entretien.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Candidat extends Acteur {
	
	Candidat(final String firstname, final String lastname, final Profil profil) {
		super(firstname, lastname, profil);
	}
}
