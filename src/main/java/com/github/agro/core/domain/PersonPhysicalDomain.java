package com.github.agro.core.domain;

import com.github.agro.core.domain.enumerated.Gender;
import com.github.agro.core.domain.enumerated.MaritalStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class PersonPhysicalDomain {

    private UUID id;
    private String nome;
    private String email;
    private String celular;
    private String nacionalidade;
    private String cpf;
    private String rg;
    private MaritalStatus maritalStatus;
    private Gender genero;
    private LocalDate dataNascimento;
    private String nomeMae;
}
