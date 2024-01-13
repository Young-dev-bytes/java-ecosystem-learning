package com.design.pattern.solid.dip;

// A. high-level modules should not  depend on low-level modules.
// both should depend on abstracts.

// B. Abstractions should not depend on details.
// details should depend on abstractions.

import java.util.ArrayList;
import java.util.List;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {

    public String name;

    public Person(String name) {
        this.name = name;
    }
}

class Relationships {

    // private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();
}

public class Demo {
}
