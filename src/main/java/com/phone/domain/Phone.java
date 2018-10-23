package com.phone.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "phones")
@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Phone implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable =  false, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "price")
    private BigDecimal price;
}
