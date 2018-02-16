package com.esgi.ddd.recrutement.planification_entretien;

import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur.ConsultantRecruteurRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.*;

import java.util.Optional;

public class PlanificationEntretienImpl implements PlanificationEntretien {
	
	private final IdentityProvider<Long> identityProvider;
	
	private final ConsultantRecruteurRepository consultantRecruteurRepository;
	private final EntretienRepository entretienRepository;
	
	public PlanificationEntretienImpl(final IdentityProvider<Long> identityProvider, final ConsultantRecruteurRepository consultantRecruteurRepository, final EntretienRepository entretienRepository) {
		this.identityProvider = identityProvider;
		this.consultantRecruteurRepository = consultantRecruteurRepository;
		this.entretienRepository = entretienRepository;
	}
	
	@Override
	public Optional<ConsultantRecruteur> trouverConsultantRecruteur(final Candidat candidat, final Creneau creneau) {
		return consultantRecruteurRepository.getAll()
			.stream()
			.filter(consultantRecruteur -> consultantRecruteur.peutTester(candidat))
			.filter(consultantRecruteur -> consultantRecruteur.estDisponible(entretienRepository, creneau))
			.findFirst();
	}
	
	@Override
	public Entretien planifierEntretien(final Candidat candidat, final Creneau creneau) {
		final Optional<ConsultantRecruteur> consultantRecruteur = trouverConsultantRecruteur(candidat, creneau);
		
		if(!consultantRecruteur.isPresent())
			throw new IllegalStateException("No ConsultantRecruteur could be found for this Candidat at this Creneau");
		
		final EntretienId id = new EntretienId(identityProvider);
		final Entretien entretien = new Entretien(id, candidat, consultantRecruteur.get(), creneau, entretienRepository);
		
		return entretienRepository.save(entretien);
	}
}
