package com.github.agro.adapter.outbound.persistence.repository;

import com.github.agro.adapter.outbound.persistence.entity.PersonPhysical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonPhysical, UUID> {
}
