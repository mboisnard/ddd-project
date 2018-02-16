package com.esgi.ddd.recrutement.planification_entretien;

import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Creneau;

import java.util.Optional;

public interface ChercherConsultantRecruteur {
	Optional<ConsultantRecruteur> chercherConsultantRecruteur(final Candidat candidat, final Creneau creneau);
}
