package com.in28minutes.database.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@NamedQuery(name = "find_all_persons", query = "select p from Person p")
@NamedQuery(name = "find_specific", query = "select  p.name, p.location from Person p")
public class Person {

    @Id
    // @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private Date birthDate;

    public Person() {

    }

    public Person(Integer id, String name, String location, Date birthDate) {
        super();
        // System.out.println("has args constructor");
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public Person(String name, String location, Date birthDate) {
        super();
        // System.out.println("has args constructor");
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("\nPerson [id=%s, name=%s, location=%s, birthDate=%s]", id, name, location, birthDate);
    }


}
