package com.gourav.ecommerce.service;

import com.gourav.ecommerce.repository.OrderRepository;
import com.gourav.ecommerce.repository.ProductRepository;
import com.gourav.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {
    private final OrderRepository orderRepository; private final UserRepository userRepository; private final ProductRepository productRepository; private final CacheService cacheService;
    public DashboardService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, CacheService cacheService){this.orderRepository=orderRepository;this.userRepository=userRepository;this.productRepository=productRepository;this.cacheService=cacheService;}
    @SuppressWarnings("unchecked")
    public Map<String, Object> stats() {
        Object cached = cacheService.get("dashboard"); if (cached != null) return (Map<String, Object>) cached;
        var orders = orderRepository.findAll(); BigDecimal revenue = orders.stream().map(o -> o.getTotal()==null?BigDecimal.ZERO:o.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String,Object> map = new HashMap<>(); map.put("totalOrders", orders.size()); map.put("totalRevenue", revenue); map.put("totalUsers", userRepository.count()); map.put("totalProducts", productRepository.count());
        cacheService.put("dashboard", map); return map;
    }
}
