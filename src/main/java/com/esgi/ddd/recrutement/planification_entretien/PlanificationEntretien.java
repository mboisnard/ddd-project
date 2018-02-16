package com.esgi.ddd.recrutement.planification_entretien;

import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Creneau;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;

import java.util.Optional;

public interface PlanificationEntretien {
	
	Optional<ConsultantRecruteur> trouverConsultantRecruteur(final Candidat candidat, final Creneau creneau);
	Entretien planifierEntretien(final Candidat candidat, final Creneau creneau);
}
