package com.gourav.ecommerce.controller;

import com.gourav.ecommerce.dto.OrderDtos;
import com.gourav.ecommerce.model.OrderEntity;
import com.gourav.ecommerce.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){this.orderService=orderService;}
    @PostMapping("/checkout") public OrderEntity checkout(Authentication authentication, @RequestBody OrderDtos.CheckoutRequest request){ return orderService.checkout(authentication.getName(), request); }
    @GetMapping public List<OrderEntity> orders(Authentication authentication){ return orderService.listUserOrders(authentication.getName()); }
    @GetMapping("/{id}") public OrderEntity get(@PathVariable Long id){ return orderService.get(id); }
    @PreAuthorize("hasRole('ADMIN')") @PutMapping("/{id}/status") public OrderEntity update(@PathVariable Long id, @RequestBody OrderDtos.UpdateOrderStatusRequest request){ return orderService.updateStatus(id, request); }
}
