package com.esgi.ddd.recrutement.sourcing.infrastructure.prospect;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;
import com.esgi.ddd.recrutement.sourcing.model.prospect.ProspectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toList;

public class InMemoryProspectRepository implements ProspectRepository {
	
	private final Map<ProspectId, Prospect> store;
	
	public InMemoryProspectRepository() {
		this.store = new HashMap<>();
	}
	
	@Override
	public Prospect get(final ProspectId id) {
		return store.get(id);
	}
	
	@Override
	public List<Prospect> getAllBySkill(final String skill) {
		return store.entrySet()
			.stream()
			.map(Entry::getValue)
			.filter(prospect -> prospect.getSkill().equals(skill))
			.collect(toList());
	}
	
	@Override
	public Prospect save(final Prospect prospect) {
		store.put(prospect.getId(), prospect);
		return prospect;
	}
}
