package com.fooddelivery.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
}
