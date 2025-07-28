package com.delivery.delivery_tracking.domain.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter(AccessLevel.PRIVATE)
@Getter
@EntityScan
public class Item {

    @Id
    @EqualsAndHashCode.Exclude
    private UUID id;

    private String name;

    @Setter(AccessLevel.PACKAGE)
    private Integer quantity;

    @ManyToMany
    @Getter(AccessLevel.PRIVATE)
    private Delivery delivery;

    static Item BrandNew(String name, Integer quantity, Delivery delivery){
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setQuantity(quantity);
        item.setDelivery(delivery);
        return item;
    }
}
