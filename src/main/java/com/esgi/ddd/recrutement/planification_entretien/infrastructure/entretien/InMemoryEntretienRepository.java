package com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien;

import com.esgi.ddd.recrutement.planification_entretien.model.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.EntretienId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class InMemoryEntretienRepository implements EntretienRepository {
	
	private final Map<EntretienId, Entretien> store;
	
	public InMemoryEntretienRepository() {
		this.store = new HashMap<>();
	}
	
	@Override
	public List<Entretien> getAllByConsultantRecruteur(final ConsultantRecruteur consultantRecruteur) {
		return store.entrySet()
			.stream()
			.map(Map.Entry::getValue)
			.filter(entretien -> entretien.getConsultantRecruteur().equals(consultantRecruteur))
			.collect(toList());
	}
	
	@Override
	public Entretien save(final Entretien entretien) {
		store.put(entretien.getId(), entretien);
		return entretien;
	}
}
