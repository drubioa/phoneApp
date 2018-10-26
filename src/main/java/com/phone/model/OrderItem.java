package com.phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OrderItem {

    /**
     * The phone identifier.
     */
    @NotNull
    private String phoneId;

    /**
     * The number of elements of item.
     */
    @NotNull
    private int num;
}
