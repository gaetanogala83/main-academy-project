package com.academy.ws.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
@SequenceGenerator(name= "CLIENTS_SEQUENCE", sequenceName = "CLIENTS_SEQUENCE_ID", allocationSize = 1)
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTS_SEQUENCE")
    private Integer id;
    private String name;
    private String surname;
    private String fiscalCode;
    private String location;
    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
