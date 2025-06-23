package com.github.agro.adapter.outbound.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("JURIDICA")
public class PersonLegal extends Person {

    private String cnpj;
    private String inscricaoEstadual;
    private String nomeFantasia;
    private LocalDate dataFundacao;
}