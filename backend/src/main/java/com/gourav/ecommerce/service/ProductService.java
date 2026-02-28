package com.gourav.ecommerce.service;

import com.gourav.ecommerce.model.Product;
import com.gourav.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository; private final CacheService cacheService;
    public ProductService(ProductRepository productRepository, CacheService cacheService){this.productRepository=productRepository;this.cacheService=cacheService;}
    public Page<Product> list(int page, int size, String category, String name) { return productRepository.findByCategoryContainingIgnoreCaseAndNameContainingIgnoreCase(category==null?"":category,name==null?"":name,PageRequest.of(page,size)); }
    public Product get(Long id) { return productRepository.findById(id).orElseThrow(); }
    public Product create(Product p) { cacheService.evict("dashboard"); return productRepository.save(p); }
    public Product update(Long id, Product payload) { Product p=get(id); p.setName(payload.getName()); p.setCategory(payload.getCategory()); p.setDescription(payload.getDescription()); p.setPrice(payload.getPrice()); p.setStock(payload.getStock()); p.setImageUrl(payload.getImageUrl()); cacheService.evict("dashboard"); return productRepository.save(p); }
    public void delete(Long id) { productRepository.deleteById(id); cacheService.evict("dashboard"); }
    public Page<Product> search(String q, int page, int size) { return productRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(q,q,PageRequest.of(page,size)); }
}
