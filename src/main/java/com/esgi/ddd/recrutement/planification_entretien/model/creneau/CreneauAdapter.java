package com.esgi.ddd.recrutement.planification_entretien.model.creneau;

public interface CreneauAdapter {
	Creneau toValueObject(final CreneauDTO dto);
}
