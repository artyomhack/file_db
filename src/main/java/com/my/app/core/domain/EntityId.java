package com.my.app.core.domain;

import com.my.app.core.domain.type.Value;

import java.io.Serializable;
import java.util.Objects;

public interface EntityId<T> extends Value<T>, Comparable<EntityId<T>> {
    boolean isEmpty();

    abstract class IdAsInt extends Value.AsPositiveInt implements EntityId<Integer> {

        public static int emptyValue = 0;


        protected IdAsInt(Object src) {
            super(src, true);
        }


        @Override
        final public boolean isEmpty() {
            return this.getValue() == emptyValue;
        }

        @Override
        final public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdAsInt idAsInt = (IdAsInt) o;
            return Objects.equals(getValue(), idAsInt.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getValue(), this.getClass().getName());
        }

        @Override
        public int compareTo(EntityId<Integer> o) {
            return this.getValue().compareTo(o.getValue());
        }
    }
}
