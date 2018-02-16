package com.esgi.ddd.recrutement.planification_entretien.application;

import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauDTO;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;

public interface PlanifierEntretien {
	Entretien planifierEntretien(final Long candidatId, final CreneauDTO creneau);
}
