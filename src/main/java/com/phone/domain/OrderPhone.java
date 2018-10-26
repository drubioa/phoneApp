package com.phone.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_phones")
@Slf4j
@Getter
@Setter
@EqualsAndHashCode
public class OrderPhone {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable =  false, nullable = false)
    private UUID id;

    @ManyToOne
    private Phone phone;

    @ManyToOne
    private Order order;

    @Column(name = "number", nullable = false)
    private Integer number;

}
