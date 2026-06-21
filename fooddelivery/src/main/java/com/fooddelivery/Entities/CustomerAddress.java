package com.fooddelivery.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {
    private String street;
    private String city;
    private String building;
    private boolean isDefault;
    private Date createdDate;
    private Date updatedDate;
    private boolean  isActive;
}
