package com.epam.infohandling.model;

import java.util.Objects;

public class Leaf implements Component {

    private String value;

    public Leaf(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void operation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Leaf leaf = (Leaf) o;
        return Objects.equals(value, leaf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "value='" + value + '\'' +
                '}';
    }
}