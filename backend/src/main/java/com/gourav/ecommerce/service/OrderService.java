package com.gourav.ecommerce.service;

import com.gourav.ecommerce.dto.OrderDtos;
import com.gourav.ecommerce.model.*;
import com.gourav.ecommerce.repository.CartItemRepository;
import com.gourav.ecommerce.repository.OrderRepository;
import com.gourav.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository; private final CartItemRepository cartItemRepository; private final UserRepository userRepository; private final CacheService cacheService;
    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository, UserRepository userRepository, CacheService cacheService){this.orderRepository=orderRepository;this.cartItemRepository=cartItemRepository;this.userRepository=userRepository;this.cacheService=cacheService;}
    private User user(String email){ return userRepository.findByEmail(email).orElseThrow(); }
    public OrderEntity checkout(String email, OrderDtos.CheckoutRequest request) {
        User user = user(email); var cartItems = cartItemRepository.findByUser(user); if (cartItems.isEmpty()) throw new RuntimeException("Cart empty");
        OrderEntity order = new OrderEntity(); order.setUser(user); order.setCreatedAt(Instant.now()); order.setStatus(OrderStatus.PENDING); order.setShippingName(request.shippingName()); order.setShippingAddress(request.shippingAddress()); order.setPaymentMethod(request.paymentMethod()); order.setTotal(BigDecimal.ZERO);
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cart : cartItems) { OrderItem item = new OrderItem(); item.setOrder(order); item.setProduct(cart.getProduct()); item.setQuantity(cart.getQuantity()); item.setUnitPrice(cart.getProduct().getPrice()); order.getItems().add(item); total = total.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))); }
        order.setTotal(total); OrderEntity saved = orderRepository.save(order); cartItemRepository.deleteByUser(user); cacheService.evict("dashboard"); return saved;
    }
    public List<OrderEntity> listUserOrders(String email) { return orderRepository.findByUser(user(email)); }
    public OrderEntity get(Long id) { return orderRepository.findById(id).orElseThrow(); }
    public OrderEntity updateStatus(Long id, OrderDtos.UpdateOrderStatusRequest request){ OrderEntity order = get(id); order.setStatus(request.status()); cacheService.evict("dashboard"); return orderRepository.save(order); }
}
