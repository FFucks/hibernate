package com.ffucks.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Good pattern use LAZY when multiple child lists
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "store")
    private List<Employee> employees;

    //Good pattern use LAZY when multiple child lists
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "store")
    private List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
