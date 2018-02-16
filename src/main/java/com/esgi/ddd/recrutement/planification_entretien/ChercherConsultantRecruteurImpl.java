package com.esgi.ddd.recrutement.planification_entretien;

import com.esgi.ddd.recrutement.planification_entretien.infrastructure.consultant_recruteur.ConsultantRecruteurRepository;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.*;

import java.util.Optional;

public class ChercherConsultantRecruteurImpl implements ChercherConsultantRecruteur {
	
	private final ConsultantRecruteurRepository consultantRecruteurRepository;
	private final EntretienRepository entretienRepository;
	
	public ChercherConsultantRecruteurImpl(final ConsultantRecruteurRepository consultantRecruteurRepository, final EntretienRepository entretienRepository) {
		if(consultantRecruteurRepository == null)
			throw new IllegalArgumentException("consultantRecruteurRepository cannot be null");
		if(entretienRepository == null)
			throw new IllegalArgumentException("entretienRepository cannot be null");
		
		this.consultantRecruteurRepository = consultantRecruteurRepository;
		this.entretienRepository = entretienRepository;
	}
	
	@Override
	public Optional<ConsultantRecruteur> chercherConsultantRecruteur(final Candidat candidat, final Creneau creneau) {
		return consultantRecruteurRepository.getAll()
			.stream()
			.filter(consultantRecruteur -> consultantRecruteur.peutTester(candidat))
			.filter(consultantRecruteur -> consultantRecruteur.estDisponible(entretienRepository, creneau))
			.findFirst();
	}
}
