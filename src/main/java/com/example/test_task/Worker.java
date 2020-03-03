package com.example.test_task;

import javax.persistence.*;

@Entity
@Table (name = "Workers")
public class Worker {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private String name;
    private String surname;
    private String email;

    public Worker() {
    }

    public Worker(Position position, String name, String surname, String email) {
        this.position = position;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}