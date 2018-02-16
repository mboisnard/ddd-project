package com.esgi.ddd.recrutement.planification_entretien.model.creneau;

public class CreneauAdapterImpl implements CreneauAdapter {
	
	@Override
	public Creneau toValueObject(final CreneauDTO dto) {
		return new Creneau(dto.getStartDate(), dto.getDuration());
	}
}
