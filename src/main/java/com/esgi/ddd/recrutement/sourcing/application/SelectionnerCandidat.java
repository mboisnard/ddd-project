package com.esgi.ddd.recrutement.sourcing.application;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;

import java.util.Optional;

public interface SelectionnerCandidat {
	Optional<Prospect> selectionnerCandidat(final String skill);
}
