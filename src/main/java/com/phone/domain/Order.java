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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id: "+id.toString()+"\n");
        stringBuilder.append("customerName: "+customerName+"\n");
        stringBuilder.append("customerFirstSurname: "+customerFirstSurname+"\n");
        stringBuilder.append("phoneNumber: "+phoneNumber+"\n");
        stringBuilder.append("email: "+email+"\n");
        stringBuilder.append("creationDate: "+creationDate.toString()+"\n");


        for(OrderPhone p: orderPhones) {
            stringBuilder.append("-------------------------------------------------------------------------------\n");
            stringBuilder.append("phoneId: "+p.getPhone().getId()+"\n");
            stringBuilder.append("phone name: "+p.getPhone().getName()+"\n");
            stringBuilder.append("phone price: "+p.getPhone().getPrice()+"\n");
            stringBuilder.append("number: "+p.getNumber()+"\n");
            stringBuilder.append("-------------------------------------------------------------------------------\n");
        }

        stringBuilder.append("TOTAL "+price+"\n");
        return stringBuilder.toString();
    }

}
