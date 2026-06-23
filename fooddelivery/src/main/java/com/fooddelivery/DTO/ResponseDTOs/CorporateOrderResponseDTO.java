package com.fooddelivery.DTO.ResponseDTOs;

import com.fooddelivery.Entities.CorporateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrderResponseDTO {
    private int corporateCode;
    private String companyName;
    private String costCenter;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive;

    public static CorporateOrderResponseDTO fromEntity(CorporateOrder corporateOrder) {

        CorporateOrderResponseDTO dto = new CorporateOrderResponseDTO();
        dto.setCorporateCode(corporateOrder.getCorporateCode());
        dto.setCompanyName(corporateOrder.getCompanyName());
        dto.setCostCenter(corporateOrder.getCostCenter());
        dto.setOrderDate(corporateOrder.getOrderDate());
        dto.setStatus(corporateOrder.getStatus());
        dto.setTotalAmount(corporateOrder.getTotalAmount());
        dto.setCreatedDate(corporateOrder.getCreatedDate());
        dto.setUpdatedDate(corporateOrder.getUpdatedDate());
        dto.setActive(corporateOrder.isActive());

        return dto;
    }
}
