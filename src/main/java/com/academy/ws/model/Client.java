package com.academy.ws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
@SequenceGenerator(name= "CLIENTS_SEQUENCE", sequenceName = "CLIENTS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Client extends RepresentationModel<Client> {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTS_SEQUENCE")
    private Integer id;

    @Size(min = 5, max = 20, message = "The name is too long!")
    @NotNull(message = "The name can't be null!")
    private String name;

    @Size(min = 5, max = 30, message = "The surname is too long!")
    @NotNull(message = "The surname can't be null!")
    private String surname;
    private String fiscalCode;
    private String location;
    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
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
