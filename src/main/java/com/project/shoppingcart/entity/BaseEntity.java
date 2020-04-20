package com.project.shoppingcart.entity;

import org.hibernate.Hibernate;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    public abstract Long getId();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false;
        }

        final BaseEntity otherEntity = (BaseEntity) other;
        if (getId() == null) {
            if (otherEntity.getId() != null) {
                return false;
            }
        } else if (!getId().equals(otherEntity.getId())) {
            return false;
        }

        return true;
    }
}
