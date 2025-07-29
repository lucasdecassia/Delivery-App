package com.delivery.delivery_tracking.api.controller;

import com.delivery.delivery_tracking.api.model.CourierIdInput;
import com.delivery.delivery_tracking.api.model.DeliveryInput;
import com.delivery.delivery_tracking.domain.model.Delivery;
import com.delivery.delivery_tracking.domain.repository.DeliveryRepository;
import com.delivery.delivery_tracking.domain.service.DeliveryCheckpointService;
import com.delivery.delivery_tracking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService service;
    private final DeliveryRepository repo;
    private final DeliveryCheckpointService checkpointService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput deliveryInput){
        return service.draft(deliveryInput);
    }

    @PutMapping("/{deliveryId}")
    public Delivery edit(@PathVariable UUID deliveryId,
            @RequestBody @Valid DeliveryInput deliveryInput){
        return service.edit(deliveryId, deliveryInput);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable){
        return new PagedModel<>(repo.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId){
        return repo.findById(deliveryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId){
        checkpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    public void pickup(@PathVariable UUID deliveryId, @Valid @RequestBody CourierIdInput input){
        checkpointService.pickup(deliveryId, input.getCourierId());
    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId){
        checkpointService.complete(deliveryId);
    }

}
