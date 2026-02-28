package com.gourav.ecommerce.config;

import com.gourav.ecommerce.model.*;
import com.gourav.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;
        User admin = userRepository.save(new User(null, "admin@gouravmishra.dev", passwordEncoder.encode("Admin@123"), "Gourav Admin", Role.ADMIN));
        User customer = userRepository.save(new User(null, "customer@test.com", passwordEncoder.encode("Test@123"), "Demo Customer", Role.CUSTOMER));

        List<String> categories = List.of("Electronics", "Clothing", "Books", "Sports", "Home");
        for (int i = 1; i <= 20; i++) {
            productRepository.save(new Product(null, "Sample Product " + i, categories.get(i % categories.size()), "Portfolio-ready product description " + i,
                    BigDecimal.valueOf(19 + i * 3L), 10 + i, "https://picsum.photos/seed/" + i + "/600/400"));
        }

        var allProducts = productRepository.findAll();
        for (int i = 0; i < 5; i++) {
            OrderEntity order = new OrderEntity();
            order.setUser(customer);
            order.setCreatedAt(Instant.now().minusSeconds(86400L * i));
            order.setStatus(OrderStatus.values()[i % OrderStatus.values().length]);
            order.setShippingName("Demo Customer");
            order.setShippingAddress("123 Portfolio Street");
            order.setPaymentMethod("CARD");
            order.setTotal(BigDecimal.valueOf(100 + i * 25L));
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(allProducts.get(i));
            item.setQuantity(1 + i);
            item.setUnitPrice(allProducts.get(i).getPrice());
            order.getItems().add(item);
            orderRepository.save(order);
        }
    }
}
