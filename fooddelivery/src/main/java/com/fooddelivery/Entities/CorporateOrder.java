package com.fooddelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CorporateOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int corporateCode;

    private String companyName;
    private String costCenter;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
    @ManyToOne
    private Restaurant restaurant;
    @OneToMany
    private List<OrderItem> orderItemList;

}
