package org.dr.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dr.orderservice.enums.OrderStatus;
import org.dr.orderservice.model.Costomer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Costomer costomer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
