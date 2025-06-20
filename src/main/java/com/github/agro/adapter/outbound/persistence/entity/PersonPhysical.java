package com.github.test.adapter.outbound.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("FISICA")
public class PersonPhysical extends Person {

    private String cpf;
    private String rg;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private Gender genero;

    private LocalDate dataNascimento;

    private String nomeMae;
}
