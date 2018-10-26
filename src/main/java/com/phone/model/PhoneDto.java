package com.phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Class that represents the information about phone.
 */
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
