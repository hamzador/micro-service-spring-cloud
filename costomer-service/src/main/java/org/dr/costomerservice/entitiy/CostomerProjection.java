package org.dr.costomerservice.entitiy;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCostomer", types = Costomer.class)
public interface CostomerProjection {
    public Long getId();
    public String getName();
    public String getEmail();
}
