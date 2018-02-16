package com.esgi.ddd.recrutement.planification_entretien.infrastructure.candidat;

import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.CandidatId;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.ProspectRepository;
import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;
import com.esgi.ddd.recrutement.sourcing.model.prospect.ProspectId;

public class InMemoryCandidatRepository implements CandidatRepository {
	
	private final ProspectRepository prospectRepository;
	
	/**
	 * ATTENTION: le CandidatRepository ne dépend du ProspectRepository que pour simplifier cette implémentation en
	 * simulant une "base de données" partagée.
 	 */
	public InMemoryCandidatRepository(final ProspectRepository prospectRepository) {
		this.prospectRepository = prospectRepository;
	}
	
	@Override
	public Candidat get(final CandidatId id) {
		final Prospect prospect = prospectRepository.get(new ProspectId(id.getId()));
		
		final Profil profil = new Profil(prospect.getSkill(), prospect.getExperience());
		final Candidat candidat = new Candidat(id, prospect.getFirstname(), prospect.getLastname(), profil);
		
		return candidat;
	}
}
