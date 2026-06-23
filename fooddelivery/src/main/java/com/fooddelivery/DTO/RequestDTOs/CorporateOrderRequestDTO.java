package com.fooddelivery.DTO.RequestDTOs;

import com.fooddelivery.Entities.CorporateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateOrderRequestDTO {
    private String companyName;
    private String costCenter;
    private String status;
    private double totalAmount;

    public CorporateOrder toEntity() {
        CorporateOrder corporateOrder = new CorporateOrder();

        corporateOrder.setCompanyName(companyName);
        corporateOrder.setCostCenter(costCenter);
        corporateOrder.setStatus(status);
        corporateOrder.setTotalAmount(totalAmount);

        return corporateOrder;
    }

    public void applyTo(CorporateOrder corporateOrder) {
        corporateOrder.setCompanyName(companyName);
        corporateOrder.setCostCenter(costCenter);
        corporateOrder.setStatus(status);
        corporateOrder.setTotalAmount(totalAmount);
    }
}
