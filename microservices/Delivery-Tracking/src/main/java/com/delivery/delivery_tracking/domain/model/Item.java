package com.delivery.delivery_tracking.domain.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Item {

    @EqualsAndHashCode.Exclude
    private UUID id;

    private String name;

    @Setter(AccessLevel.PACKAGE)
    private Integer quantity;

    static Item BrandNew(String name, Integer quantity){
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setQuantity(quantity);
        return item;
    }
}
