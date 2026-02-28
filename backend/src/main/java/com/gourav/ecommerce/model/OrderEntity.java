package com.gourav.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) private User user;
    private Instant createdAt;
    private BigDecimal total;
    @Enumerated(EnumType.STRING) private OrderStatus status;
    private String shippingName;
    private String shippingAddress;
    private String paymentMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    public OrderEntity(){}
    public OrderEntity(Long id,User user,Instant createdAt,BigDecimal total,OrderStatus status,String shippingName,String shippingAddress,String paymentMethod){this.id=id;this.user=user;this.createdAt=createdAt;this.total=total;this.status=status;this.shippingName=shippingName;this.shippingAddress=shippingAddress;this.paymentMethod=paymentMethod;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getUser(){return user;} public void setUser(User user){this.user=user;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant createdAt){this.createdAt=createdAt;}
    public BigDecimal getTotal(){return total;} public void setTotal(BigDecimal total){this.total=total;}
    public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus status){this.status=status;}
    public String getShippingName(){return shippingName;} public void setShippingName(String shippingName){this.shippingName=shippingName;}
    public String getShippingAddress(){return shippingAddress;} public void setShippingAddress(String shippingAddress){this.shippingAddress=shippingAddress;}
    public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String paymentMethod){this.paymentMethod=paymentMethod;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){this.items=items;}
}
