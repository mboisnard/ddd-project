package com.esgi.ddd.recrutement.model;

import java.io.Serializable;

public interface IdentityProvider<T extends Serializable> {
	T generate();
}
