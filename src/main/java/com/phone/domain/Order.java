package com.phone.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
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

    @Column(name = "name")
    private String name;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerFirstSurname")
    private String customerFirstSurname;

    @Column(name = "customerSecondSurname")
    private String customerSecondSurname;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "fk_orders_phones")
    private Set<Phone> orderPhones;*/

}
