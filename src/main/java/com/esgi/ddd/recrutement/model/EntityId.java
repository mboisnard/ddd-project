package com.esgi.ddd.recrutement.model;

public abstract class EntityId extends ValueObject {
	
	private final Long id;
	
	public EntityId(final Long id) {
		this.id = id;
	}
	
	public EntityId(final IdentityProvider<Long> identityProvider) {
		this.id = identityProvider.generate();
	}
}
