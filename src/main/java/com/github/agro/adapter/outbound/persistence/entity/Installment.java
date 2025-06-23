package com.github.agro.adapter.outbound.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    private Integer numeroParcela;
    private LocalDate dataVencimento;
    private BigDecimal valorParcela;
    private Boolean quitada = false;
}