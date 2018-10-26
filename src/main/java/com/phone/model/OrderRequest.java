package com.phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Order requests definition.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OrderRequest {

    @NotNull
    CustomerDto customerDto;

    /**
     * List of phones
     */
    List<OrderItem> items;
}
