package com.github.agro.core.service;

import com.github.agro.adapter.inbound.DTO.PersonPhysicalRequestDTO;
import com.github.agro.core.domain.PersonPhysicalDomain;

public interface PersonService {
    PersonPhysicalDomain cadastrarPessoaFisica(PersonPhysicalRequestDTO dto);
}
