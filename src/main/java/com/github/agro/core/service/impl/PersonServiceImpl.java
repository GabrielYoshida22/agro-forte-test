package com.github.agro.core.service.impl;

import com.github.agro.adapter.inbound.DTO.PersonPhysicalRequestDTO;
import com.github.agro.adapter.outbound.persistence.entity.PersonPhysical;
import com.github.agro.adapter.outbound.persistence.repository.PersonRepository;
import com.github.agro.core.domain.PersonPhysicalDomain;
import com.github.agro.core.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PersonPhysicalDomain cadastrarPessoaFisica(PersonPhysicalRequestDTO dto) {

        PersonPhysical entity = modelMapper.map(dto, PersonPhysical.class);
        PersonPhysical saved = repository.save(entity);
        return modelMapper.map(saved, PersonPhysicalDomain.class);
    }
}
