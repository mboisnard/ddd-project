package com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur;

import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;

import java.util.List;

public interface ConsultantRecruteurRepository {
	List<ConsultantRecruteur> getAll();
	ConsultantRecruteur save(final ConsultantRecruteur consultantRecruteur);
}
