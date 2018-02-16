package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.ProspectRepository;
import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;
import com.esgi.ddd.recrutement.sourcing.model.prospect.ProspectId;

public class EnregistrerProspectImpl implements EnregistrerProspect {
	
	private final IdentityProvider<Long> identityProvider;
	private final ProspectRepository prospectRepository;
	
	public EnregistrerProspectImpl(final IdentityProvider<Long> identityProvider, final ProspectRepository prospectRepository) {
		this.identityProvider = identityProvider;
		this.prospectRepository = prospectRepository;
	}
	
	@Override
	public Prospect enregistrerProspect(final String firstname, final String lastname, final String skill, final Double experience) {
		final ProspectId id = new ProspectId(identityProvider);
		final Prospect prospect = new Prospect(id, firstname, lastname, skill, experience);
		
		return prospect.enregistrer(prospectRepository);
	}
}
