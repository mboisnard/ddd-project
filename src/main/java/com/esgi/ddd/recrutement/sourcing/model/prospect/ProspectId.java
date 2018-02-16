package com.esgi.ddd.recrutement.sourcing.model.prospect;

import com.esgi.ddd.recrutement.core.model.EntityId;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;

public class ProspectId extends EntityId {
	public ProspectId(final Long id) {
		super(id);
	}
	
	public ProspectId(final IdentityProvider<Long> identityProvider) {
		super(identityProvider);
	}
}
