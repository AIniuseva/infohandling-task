package com.epam.infohandling.model;

public interface Component {

    void add(Component component);

    void remove(Component component);

    Component getChild(int index);
}
