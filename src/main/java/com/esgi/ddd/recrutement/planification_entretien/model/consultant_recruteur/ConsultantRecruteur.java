package com.esgi.ddd.recrutement.planification_entretien.model.consultant_recruteur;

import com.esgi.ddd.recrutement.core.model.Entity;
import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.profil.Profil;
import com.esgi.ddd.recrutement.planification_entretien.model.candidat.Candidat;
import com.esgi.ddd.recrutement.planification_entretien.model.creneau.Creneau;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ConsultantRecruteur extends Entity<ConsultantRecruteurId> {
	
	private final String firstname;
	private final String lastname;
	
	private final Profil profil;
	
	ConsultantRecruteur(final ConsultantRecruteurId id, final String firstname, final String lastname, final Profil profil) {
		super(id);
		
		if(firstname == null || firstname.isEmpty())
			throw new IllegalArgumentException("firstname cannot be null or empty");
		if(lastname == null || lastname.isEmpty())
			throw new IllegalArgumentException("lastname cannot be null or empty");
		if(profil == null)
			throw new IllegalArgumentException("profil cannot be null");
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.profil = profil;
	}
	
	public boolean peutTester(final Candidat candidat) {
		final Profil profilRecruteur = this.getProfil();
		final Profil profilCandidat = candidat.getProfil();
		
		return profilRecruteur.compareTo(profilCandidat) == 0;
	}
	
	public boolean estDisponible(final EntretienRepository entretienRepository, final Creneau creneau) {
		return entretienRepository.getAllByConsultantRecruteur(this)
			.stream()
			.map(Entretien::getCreneau)
			.noneMatch(creneau::collidesWith);
	}
}
