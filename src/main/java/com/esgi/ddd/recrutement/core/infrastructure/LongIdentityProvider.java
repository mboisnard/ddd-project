package com.esgi.ddd.recrutement.core.infrastructure;

import com.esgi.ddd.recrutement.core.model.IdentityProvider;

import java.util.concurrent.atomic.AtomicLong;

public class LongIdentityProvider implements IdentityProvider<Long> {
	
	private static final Long DEFAULT_VALUE = 0L;
	
	private final AtomicLong increment;
	
	public LongIdentityProvider() {
		this.increment = new AtomicLong(DEFAULT_VALUE);
	}
	
	@Override
	public Long generate() {
		return increment.incrementAndGet();
	}
}
