package com.gourav.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDtos {
    public record AddToCartRequest(Long productId, Integer quantity) {}
    public record UpdateCartRequest(Integer quantity) {}
    public record CartItemResponse(Long id, Long productId, String name, BigDecimal price, Integer quantity) {}
    public record CartResponse(List<CartItemResponse> items, BigDecimal total, int itemCount) {}
}
