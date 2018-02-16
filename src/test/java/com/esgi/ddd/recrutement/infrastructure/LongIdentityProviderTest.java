package com.esgi.ddd.recrutement.infrastructure;

import com.esgi.ddd.recrutement.core.infrastructure.LongIdentityProvider;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongIdentityProviderTest {
	
	@Test
	public void shouldIncrementIdentity() {
		final IdentityProvider<Long> provider = new LongIdentityProvider();
		
		assertThat(provider.generate()).isEqualTo(1L);
		assertThat(provider.generate()).isEqualTo(2L);
	}
}