package org.dr.costomerservice.repository;

import org.dr.costomerservice.entitiy.Costomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CostomerRepository extends JpaRepository<Costomer, Long> {

}
