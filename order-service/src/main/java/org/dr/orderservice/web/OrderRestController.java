package org.dr.orderservice.web;

import org.dr.orderservice.entity.Order;
import org.dr.orderservice.model.Costomer;
import org.dr.orderservice.model.Product;
import org.dr.orderservice.repository.OrderRepository;
import org.dr.orderservice.repository.ProductItemRepository;
import org.dr.orderservice.service.CostomerRestClientService;
import org.dr.orderservice.service.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CostomerRestClientService costomerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository,
                               ProductItemRepository productItemRepository,
                               CostomerRestClientService costomerRestClientService,
                               InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.costomerRestClientService = costomerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(Long id){
        Order order = orderRepository.findById(id).get();
        Costomer costomer = costomerRestClientService.customerById(order.getCustomerId());
        order.setCostomer(costomer);
        order.getProductItems().forEach(
                pi -> {
                    Product product = inventoryRestClientService.productById(pi.getProductId());
                    pi.setProduct(product);
                }
        );
        return order;
    }
}
