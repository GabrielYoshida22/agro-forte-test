package com.github.test.adapter.outbound.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String email;
    private String celular;
    private String nacionalidade;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
}
