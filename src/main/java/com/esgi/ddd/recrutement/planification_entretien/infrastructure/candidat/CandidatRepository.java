package com.esgi.ddd.recrutement.planification_entretien.infrastructure.candidat;

import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.CandidatId;

public interface CandidatRepository {
	Candidat get(final CandidatId id);
}
