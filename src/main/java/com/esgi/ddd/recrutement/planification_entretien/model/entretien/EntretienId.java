package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.EntityId;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;

public class EntretienId extends EntityId {
	
	public EntretienId(final Long id) {
		super(id);
	}
	
	public EntretienId(final IdentityProvider<Long> identityProvider) {
		super(identityProvider);
	}
}
