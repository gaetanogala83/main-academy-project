package com.academy.ws.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name= "ORDERS_SEQUENCE", sequenceName = "ORDERS_SEQUENCE_ID", allocationSize = 1)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Order extends RepresentationModel<Order> {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_SEQUENCE")
    private Integer id;

    @Digits(integer = 10, fraction = 3, message = "The price value is wrong!")
    @NotNull(message = "The price can't be null!")
    private BigDecimal price;
    @Size(min = 5, max = 50, message = "The description is too long!")
    @NotNull(message = "The description can't be null!")
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
