package org.dr.orderservice.service;

import org.dr.orderservice.model.Costomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "costomer-service")
public interface CostomerRestClientService {

    @GetMapping("/costomers/{id}?projection=fullCostomer")
    public Costomer customerById(@PathVariable Long id);

    @GetMapping("/costomers?projection=fullCostomer")
    public PagedModel<Costomer> allCustomers();
}
