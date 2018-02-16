package com.esgi.ddd.recrutement.planification_entretien.model.entretien;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.core.model.IdentityProvider;
import com.esgi.ddd.recrutement.planification_entretien.application.ChercherConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur.ConsultantRecruteur;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.Creneau;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Entretien extends Entity<EntretienId> {

	private enum State {PENDING, CONFIRMED, CANCELED}
	
	private final Candidat candidat;
	private final ConsultantRecruteur consultantRecruteur;
	private final Creneau creneau;
	
	private State state;
	
	public Entretien(final IdentityProvider<Long> identityProvider, final Candidat candidat, final Creneau creneau, final ChercherConsultantRecruteur chercherConsultantRecruteur) {
		super(new EntretienId(identityProvider));
		
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
		this.state = State.PENDING;
	}
	
	public Entretien enregistrer(final EntretienRepository entretienRepository) {
		if(entretienRepository == null)
			throw new IllegalArgumentException("entretienRepository cannot be null");
		
		return entretienRepository.save(this);
	}
	
	public Entretien confirmer() {
		if(state != State.PENDING)
			throw new IllegalStateException("Entretien must be pending in order to be confirmed");
		
		this.state = State.CONFIRMED;
		return this;
	}
	
	public Entretien annuler() {
		if(state == State.CANCELED)
			throw new IllegalStateException("Entretien is already cancelled");
		
		this.state = State.CANCELED;
		return this;
	}
	
	public Entretien replannifier(final IdentityProvider<Long> provider, final Creneau creneau, final ChercherConsultantRecruteur chercherConsultantRecruteur) {
		this.annuler();
		return new Entretien(provider, this.candidat, creneau, chercherConsultantRecruteur);
	}
}
