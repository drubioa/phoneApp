package com.phone.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable =  false, nullable = false)
    private UUID id;

    @Column(name = "customername")
    private String customerName;

    @Column(name = "customerfirstsurname")
    private String customerFirstSurname;

    @Column(name = "customersecondsurname")
    private String customerSecondSurname;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "creation_date")
    private Instant creationDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderPhone> orderPhones;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
