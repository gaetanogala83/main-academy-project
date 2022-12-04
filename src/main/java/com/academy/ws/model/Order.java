package com.academy.ws.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name= "ORDERS_SEQUENCE", sequenceName = "ORDERS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_SEQUENCE")
    private Integer id;
    private BigDecimal price;

    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Client client;
    @Transient
    private Integer clientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
