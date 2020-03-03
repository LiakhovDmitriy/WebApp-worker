package com.example.test_task;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Positions")
public class Position {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<Worker> workers = new ArrayList<Worker>();

    public Position() {
    }

    public Position(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
