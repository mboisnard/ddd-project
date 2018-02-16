package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.Creneau;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Entretien extends Entity<EntretienId> {

	private final Candidat candidat;
	private final ConsultantRecruteur consultantRecruteur;
	private final Creneau creneau;
	
	public Entretien(final EntretienId id, final Candidat candidat, final ConsultantRecruteur consultantRecruteur, final Creneau creneau, final EntretienRepository entretienRepository) {
		super(id);
		
		if(id == null)
			throw new IllegalArgumentException("id cannot be null");
		if(consultantRecruteur == null)
			throw new IllegalArgumentException("consultantRecruteur cannot be null");
		if(candidat == null)
			throw new IllegalArgumentException("candidat cannot be null");
		if(creneau == null)
			throw new IllegalArgumentException("creneau cannot be null");
		if(entretienRepository == null)
			throw new IllegalArgumentException("entretienRepository cannot be null");
		if (!consultantRecruteur.peutTester(candidat))
			throw new IllegalStateException("consultantRecruteur cannot test candidat");
		if(!consultantRecruteur.estDisponible(entretienRepository, creneau))
			throw new IllegalStateException("consultantRecruteur is not available");
		
		this.candidat = candidat;
		this.consultantRecruteur = consultantRecruteur;
		this.creneau = creneau;
		
		entretienRepository.save(this);
	}
}
