package org.dr.inventoryservice.entity;

import org.springframework.data.rest.core.config.Projection;

// by default RepositoryRestResource nit showing the id this is the aim of the projection
@Projection(name = "fullProduct", types = Product.class)
public interface ProductProjection {
    public Long getId();
    public String getName();
    public double getPrice();
    public int getQuantity();
}
