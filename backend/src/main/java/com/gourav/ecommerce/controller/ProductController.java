package com.gourav.ecommerce.controller;

import com.gourav.ecommerce.model.Product;
import com.gourav.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){this.productService=productService;}
    @GetMapping public Page<Product> list(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(required = false) String category,@RequestParam(required = false) String name){ return productService.list(page,size,category,name);}    
    @GetMapping("/{id}") public Product get(@PathVariable Long id){ return productService.get(id); }
    @PreAuthorize("hasRole('ADMIN')") @PostMapping public Product create(@RequestBody Product product){ return productService.create(product); }
    @PreAuthorize("hasRole('ADMIN')") @PutMapping("/{id}") public Product update(@PathVariable Long id,@RequestBody Product product){ return productService.update(id, product); }
    @PreAuthorize("hasRole('ADMIN')") @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ productService.delete(id); }
    @GetMapping("/search") public Page<Product> search(@RequestParam String q,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){ return productService.search(q,page,size); }
}
