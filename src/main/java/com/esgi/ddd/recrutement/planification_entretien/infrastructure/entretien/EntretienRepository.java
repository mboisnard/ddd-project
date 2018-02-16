package com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien;

import com.esgi.ddd.recrutement.planification_entretien.model.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;

import java.util.List;

public interface EntretienRepository {
	List<Entretien> getAllByConsultantRecruteur(final ConsultantRecruteur consultantRecruteur);
	Entretien save(final Entretien entretien);
}
