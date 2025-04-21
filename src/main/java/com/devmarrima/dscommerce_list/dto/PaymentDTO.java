package com.devmarrima.dscommerce_list.dto;

import java.time.Instant;

import com.devmarrima.dscommerce_list.entities.Payment;

public class PaymentDTO {
    private Long id;
    private Instant moment;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

}
