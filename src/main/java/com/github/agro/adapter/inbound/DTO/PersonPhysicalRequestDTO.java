package com.github.agro.adapter.inbound.DTO;

import com.github.agro.core.domain.enumerated.Gender;
import com.github.agro.core.domain.enumerated.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;


@Schema(description = "Dados para cadastro de pessoa física")
@Builder
public record PersonPhysicalRequestDTO(
        @Schema(example = "João da Silva")
        @NotBlank String nome,

        @Schema(example = "joao@email.com")
        @NotBlank @Email String email,

        @Schema(example = "11999999999")
        @NotBlank String celular,

        @Schema(example = "Brasileira")
        @NotBlank String nacionalidade,

        @Schema(example = "12345678900")
        @NotBlank String cpf,

        @Schema(example = "1234567")
        @NotBlank String rg,

        @NotNull MaritalStatus maritalStatus,

        @NotNull Gender genero,

        @Schema(example = "1990-01-01")
        @NotNull LocalDate dataNascimento,

        @Schema(example = "Maria da Silva")
        @NotBlank String nomeMae
) {}
