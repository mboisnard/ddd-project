package com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur;

import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteurId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toList;

public class InMemoryConsultantRecruteurRepository implements ConsultantRecruteurRepository {
	
	private final Map<ConsultantRecruteurId, ConsultantRecruteur> store;
	
	public InMemoryConsultantRecruteurRepository() {
		store = new HashMap<>();
	}
	
	@Override
	public List<ConsultantRecruteur> getAll() {
		return store.entrySet()
			.stream()
			.map(Entry::getValue)
			.collect(toList());
	}
	
	@Override
	public ConsultantRecruteur save(final ConsultantRecruteur consultantRecruteur) {
		store.put(consultantRecruteur.getId(), consultantRecruteur);
		return consultantRecruteur;
	}
}
