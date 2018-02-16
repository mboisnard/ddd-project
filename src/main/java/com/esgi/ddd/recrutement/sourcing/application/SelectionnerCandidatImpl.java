package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;

import java.util.Optional;

public class SelectionnerCandidatImpl implements SelectionnerCandidat {
	
	private final RechercherProfil rechercherProfil;
	
	public SelectionnerCandidatImpl(final RechercherProfil rechercherProfil) {
		this.rechercherProfil = rechercherProfil;
	}
	
	@Override
	public Optional<Prospect> selectionnerCandidat(final String skill) {
		return rechercherProfil.rechercherProspectsParProfil(skill).stream().findFirst();
	}
}
