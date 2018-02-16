package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.planification_entretien.ChercherConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Entretien extends Entity<EntretienId> {

	private final Candidat candidat;
	private final ConsultantRecruteur consultantRecruteur;
	private final Creneau creneau;
	
	public Entretien(final EntretienId id, final Candidat candidat, final Creneau creneau, final ChercherConsultantRecruteur chercherConsultantRecruteur) {
		super(id);
		
		if(id == null)
			throw new IllegalArgumentException("id cannot be null");
		if(candidat == null)
			throw new IllegalArgumentException("candidat cannot be null");
		if(creneau == null)
			throw new IllegalArgumentException("creneau cannot be null");
		if(chercherConsultantRecruteur == null)
			throw new IllegalArgumentException("chercherConsultantRecruteur cannot be null");
		
		final Optional<ConsultantRecruteur> consultantRecruteur = chercherConsultantRecruteur.chercherConsultantRecruteur(candidat, creneau);
		
		if(!consultantRecruteur.isPresent())
			throw new IllegalStateException("No ConsultantRecruteur could be found");
		
		this.candidat = candidat;
		this.consultantRecruteur = consultantRecruteur.get();
		this.creneau = creneau;
	}
	
	public Entretien enregistrer(final EntretienRepository entretienRepository) {
		return entretienRepository.save(this);
	}
}
