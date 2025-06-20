package com.github.test.adapter.outbound.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    private LocalDate dataInicio;
    private LocalDate dataEmissao;
    private LocalDate dataFim;

    private Integer qtdParcelas;
    private LocalDate dataPrimeiraParcela;
    private Integer tempoCarencia;

    private BigDecimal valorTotal;
    private BigDecimal taxaMensal;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Installment> parcelas = new ArrayList<>();
}
