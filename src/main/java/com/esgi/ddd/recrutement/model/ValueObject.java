package com.esgi.ddd.recrutement.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class ValueObject implements Comparable<ValueObject> {

    @Override
    public int compareTo(final ValueObject valueObject) {

        if (!this.getClass().isInstance(valueObject))
            throw new IllegalArgumentException();

        return this.hashCode() - valueObject.hashCode();
    }
}