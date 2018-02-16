package com.esgi.ddd.recrutement.core.model;

import java.io.Serializable;

public interface IdentityProvider<T extends Serializable> {
	T generate();
}
