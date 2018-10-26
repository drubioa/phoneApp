package com.phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Class that represents the information about a customer.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerDto {

    @NotNull
    private String name;

    @NotNull
    private String firstSurname;

    private String secondSurname;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

}
