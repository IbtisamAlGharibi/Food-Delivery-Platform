package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrder {
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
