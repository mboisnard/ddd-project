package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.EntityId;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;

public class ConsultantRecruteurId extends EntityId {
	public ConsultantRecruteurId(final Long id) {
		super(id);
	}
	
	public ConsultantRecruteurId(final IdentityProvider<Long> identityProvider) {
		super(identityProvider);
	}
}
