package com.phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PhoneDto implements Serializable {

    private String id;

    private String name;

    private String reference;

    private String price;
}
