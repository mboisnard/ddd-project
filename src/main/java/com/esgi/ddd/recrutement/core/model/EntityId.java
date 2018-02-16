package com.esgi.ddd.recrutement.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class EntityId extends ValueObject {
	
	private final Long id;
	
	public EntityId(final Long id) {
		this.id = id;
	}
	
	public EntityId(final IdentityProvider<Long> identityProvider) {
		if(identityProvider == null)
			throw new IllegalArgumentException("identityProvider cannot be null");
		
		this.id = identityProvider.generate();
	}
}
