package com.esgi.ddd.recrutement.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public abstract class Entity<ID extends EntityId> {

    private final ID id;
}