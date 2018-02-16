package com.esgi.ddd.recrutement.planification_entretien.application;

import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.Creneau;

import java.util.Optional;

public interface ChercherConsultantRecruteur {
	Optional<ConsultantRecruteur> chercherConsultantRecruteur(final Candidat candidat, final Creneau creneau);
}
