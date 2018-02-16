package com.esgi.ddd.recrutement.planification_entretien.application;

import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.candidat.CandidatRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.CandidatId;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.Creneau;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauAdapter;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.CreneauDTO;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;

public class PlanifierEntretienImpl implements PlanifierEntretien {
	
	private final CreneauAdapter creneauAdapter;
	private final ChercherConsultantRecruteur chercherConsultantRecruteur;
	private final IdentityProvider<Long> identityProvider;
	
	private final CandidatRepository candidatRepository;
	private final EntretienRepository entretienRepository;
	
	public PlanifierEntretienImpl(
		final CreneauAdapter creneauAdapter,
		final ChercherConsultantRecruteur chercherConsultantRecruteur,
		final IdentityProvider<Long> identityProvider,
		final CandidatRepository candidatRepository,
		final EntretienRepository entretienRepository
	) {
		this.creneauAdapter = creneauAdapter;
		this.chercherConsultantRecruteur = chercherConsultantRecruteur;
		this.identityProvider = identityProvider;
		this.candidatRepository = candidatRepository;
		this.entretienRepository = entretienRepository;
	}
	
	@Override
	public Entretien planifierEntretien(final Long candidatId, final CreneauDTO creneauDTO) {
		final Candidat candidat = candidatRepository.get(new CandidatId(candidatId));
		final Creneau creneau = creneauAdapter.toValueObject(creneauDTO);
		
		final Entretien entretien = new Entretien(identityProvider, candidat, creneau, chercherConsultantRecruteur);
		return entretien.enregistrer(entretienRepository);
	}
}
