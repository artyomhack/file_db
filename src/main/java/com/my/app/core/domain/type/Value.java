package com.my.app.core.domain.type;

import com.my.app.core.domain.ValueObject;

import java.io.Serializable;

public interface Value<T> extends ValueObject {

    T getValue();

    abstract class Abstract<T> implements Value<T> {

        @Override
        public String toString() {
            return getValue().toString();
        }
    }

    abstract class AsNumber<T extends Number> implements Value<T> {

        private final T value;

        protected AsNumber(T value) {
            this.value = value;
        }

        @Override
        final public T getValue() {
            return value;
        }
    }

    abstract class AsInt extends AsNumber<Integer> {

        protected AsInt(Object src) {
            super(Integer.parseInt(src.toString()));
        }
    }

    abstract class AsPositiveNumber<T extends Number>  extends AsNumber<T> {

        protected AsPositiveNumber(T value, boolean includeZero) {
            super(value);
        }

        private void checkForPositive(T value, boolean includeZero) {
            var comparable = 0;
            if (includeZero) {
                comparable = -1;
            }

            if(comparable > value.doubleValue()) {
                var message = new StringBuilder("Must be greater then 0");
                if (includeZero) {
                    message.append(" include 0");
                }
                throw new IllegalArgumentException(message.toString());
            }
        }
    }

    abstract class AsPositiveInt extends AsPositiveNumber<Integer> {
        protected AsPositiveInt(Object src, boolean includeZero) {
            super(Integer.parseInt(src.toString()), includeZero);
        }
    }
}
