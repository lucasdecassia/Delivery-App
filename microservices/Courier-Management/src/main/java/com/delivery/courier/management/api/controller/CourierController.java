package com.delivery.courier.management.api.controller;

import com.delivery.courier.management.api.model.CourierInput;
import com.delivery.courier.management.domain.model.Courier;
import com.delivery.courier.management.domain.repository.CourierRepository;
import com.delivery.courier.management.domain.service.CourierRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequestMapping("/api/v1/couriers")
@RestController
@RequiredArgsConstructor
public class CourierController {

    private final CourierRegistrationService service;
    private final CourierRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Courier created(@Valid @RequestBody CourierInput input){
        return service.create(input);
    }

    @PutMapping("/{courierId}")
    public Courier edit(@PathVariable UUID courierId, @Valid @RequestBody CourierInput input){
        return service.update(courierId, input);
    }

    @GetMapping
    public PagedModel<Courier> findAll(@PageableDefault Pageable pageable){
        return new PagedModel<>(repo.findAll(pageable));
    }

    @GetMapping("/{courierId}")
    public Courier findById(@PathVariable UUID courierId){
        return repo.findById(courierId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
