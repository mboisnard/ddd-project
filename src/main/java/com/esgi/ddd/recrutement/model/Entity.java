package com.esgi.ddd.recrutement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Entity<ID extends ValueObject> {

    private final ID id;
}