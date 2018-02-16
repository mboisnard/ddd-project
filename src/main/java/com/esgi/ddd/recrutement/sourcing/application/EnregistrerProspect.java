package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;

public interface EnregistrerProspect {
	Prospect enregistrerProspect(final String firstname, final String lastname, final String skill, final Double experience);
}
