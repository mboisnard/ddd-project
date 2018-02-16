package com.esgi.ddd.recrutement.planification_entretien.model;

import com.esgi.ddd.recrutement.planification_entretien.infrastructure.entretien.EntretienRepository;
import com.esgi.ddd.recrutement.planification_entretien.model.entretien.Entretien;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ConsultantRecruteur extends Acteur {
	
	ConsultantRecruteur(final String firstname, final String lastname, final Profil profil) {
		super(firstname, lastname, profil);
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
