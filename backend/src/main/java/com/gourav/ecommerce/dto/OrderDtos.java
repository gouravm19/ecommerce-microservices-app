package com.gourav.ecommerce.dto;

import com.gourav.ecommerce.model.OrderStatus;

public class OrderDtos {
    public record CheckoutRequest(String shippingName, String shippingAddress, String paymentMethod) {}
    public record UpdateOrderStatusRequest(OrderStatus status) {}
}
