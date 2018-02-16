package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.sourcing.infrastructure.prospect.ProspectRepository;
import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;

import java.util.List;

public class RechercherProfilImpl implements RechercherProfil {
	
	private final ProspectRepository prospectRepository;
	
	public RechercherProfilImpl(final ProspectRepository prospectRepository) {
		this.prospectRepository = prospectRepository;
	}
	
	@Override
	public List<Prospect> rechercherProspectsParProfil(final String skill) {
		if(skill == null || skill.isEmpty())
			throw new IllegalArgumentException("skill cannot be null or empty");
		
		return prospectRepository.getAllBySkill(skill);
	}
}
