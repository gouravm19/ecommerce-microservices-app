package com.gourav.ecommerce.service;

import com.gourav.ecommerce.dto.CartDtos;
import com.gourav.ecommerce.model.CartItem;
import com.gourav.ecommerce.model.Product;
import com.gourav.ecommerce.model.User;
import com.gourav.ecommerce.repository.CartItemRepository;
import com.gourav.ecommerce.repository.ProductRepository;
import com.gourav.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository; private final ProductRepository productRepository; private final UserRepository userRepository;
    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository){this.cartItemRepository=cartItemRepository;this.productRepository=productRepository;this.userRepository=userRepository;}
    private User user(String email){ return userRepository.findByEmail(email).orElseThrow(); }
    public CartDtos.CartResponse getCart(String email) { List<CartItem> items = cartItemRepository.findByUser(user(email)); var mapped = items.stream().map(i -> new CartDtos.CartItemResponse(i.getId(), i.getProduct().getId(), i.getProduct().getName(), i.getProduct().getPrice(), i.getQuantity())).toList(); BigDecimal total = mapped.stream().map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity()))).reduce(BigDecimal.ZERO, BigDecimal::add); int count = mapped.stream().mapToInt(CartDtos.CartItemResponse::quantity).sum(); return new CartDtos.CartResponse(mapped, total, count); }
    public CartDtos.CartResponse add(String email, CartDtos.AddToCartRequest request){ User u = user(email); Product p = productRepository.findById(request.productId()).orElseThrow(); CartItem item = cartItemRepository.findByUserIdAndProductId(u.getId(), p.getId()).orElse(new CartItem(null,u,p,0)); item.setQuantity(item.getQuantity()+request.quantity()); cartItemRepository.save(item); return getCart(email); }
    public CartDtos.CartResponse update(String email, Long id, CartDtos.UpdateCartRequest request){ CartItem item = cartItemRepository.findById(id).orElseThrow(); if(!item.getUser().getEmail().equals(email)) throw new RuntimeException("Forbidden"); item.setQuantity(request.quantity()); cartItemRepository.save(item); return getCart(email); }
    public void remove(String email, Long id){ CartItem item = cartItemRepository.findById(id).orElseThrow(); if(item.getUser().getEmail().equals(email)) cartItemRepository.delete(item); }
    public void clear(String email){ cartItemRepository.deleteByUser(user(email)); }
}
