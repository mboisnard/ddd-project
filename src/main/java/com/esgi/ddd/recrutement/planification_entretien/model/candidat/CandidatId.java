package com.esgi.ddd.recrutement.planification_entretien.model.candidat;

import com.esgi.ddd.recrutement.core.model.EntityId;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;

public class CandidatId extends EntityId {
	public CandidatId(final Long id) {
		super(id);
	}
	
	public CandidatId(final IdentityProvider<Long> identityProvider) {
		super(identityProvider);
	}
}
