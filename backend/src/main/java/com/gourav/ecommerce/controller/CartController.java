package com.gourav.ecommerce.controller;

import com.gourav.ecommerce.dto.CartDtos;
import com.gourav.ecommerce.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService){this.cartService=cartService;}
    @GetMapping public CartDtos.CartResponse get(Authentication authentication){ return cartService.getCart(authentication.getName()); }
    @PostMapping("/add") public CartDtos.CartResponse add(Authentication authentication, @RequestBody CartDtos.AddToCartRequest request){ return cartService.add(authentication.getName(), request); }
    @PutMapping("/update/{id}") public CartDtos.CartResponse update(Authentication authentication, @PathVariable Long id, @RequestBody CartDtos.UpdateCartRequest request){ return cartService.update(authentication.getName(), id, request); }
    @DeleteMapping("/remove/{id}") public void remove(Authentication authentication, @PathVariable Long id){ cartService.remove(authentication.getName(), id); }
    @DeleteMapping("/clear") public void clear(Authentication authentication){ cartService.clear(authentication.getName()); }
}
