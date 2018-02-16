package com.esgi.ddd.recrutement.sourcing.infrastructure.prospect;

import com.esgi.ddd.recrutement.sourcing.model.prospect.Prospect;
import com.esgi.ddd.recrutement.sourcing.model.prospect.ProspectId;

import java.util.List;

public interface ProspectRepository {
	Prospect get(final ProspectId id);
	List<Prospect> getAllBySkill(final String skill);
	Prospect save(final Prospect prospect);
}
