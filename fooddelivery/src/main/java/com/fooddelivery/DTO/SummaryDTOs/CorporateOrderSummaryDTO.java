package com.fooddelivery.DTO.SummaryDTOs;

import com.fooddelivery.Entities.CorporateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrderSummaryDTO {
    private int corporateCode;
    private String companyName;
    private String status;
    private double totalAmount;
    private boolean isActive;

    public static CorporateOrderSummaryDTO fromEntity(CorporateOrder corporateOrder) {

        CorporateOrderSummaryDTO dto = new CorporateOrderSummaryDTO();
        dto.setCorporateCode(corporateOrder.getCorporateCode());
        dto.setCompanyName(corporateOrder.getCompanyName());
        dto.setStatus(corporateOrder.getStatus());
        dto.setTotalAmount(corporateOrder.getTotalAmount());
        dto.setActive(corporateOrder.isActive());

        return dto;
    }
}
