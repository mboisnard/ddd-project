package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;

import java.util.List;

public interface RechercherProfil {
	List<Prospect> rechercherProspectsParProfil(final String skill);
}
