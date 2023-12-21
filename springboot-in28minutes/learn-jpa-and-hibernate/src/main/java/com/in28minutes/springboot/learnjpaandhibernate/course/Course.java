package com.in28minutes.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity(name = "course")
@NamedQuery(name = "find_all_courses",query = "select c from course c")
@NamedQuery(name = "find_name_courses",query = "select c.name from course c")
public class Course {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    private String author;

    public Course() {

    }

    public Course(long id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\nCourse [id=" + id + ", name=" + name + ", author=" + author + "]";
    }
}
