package com.fooddelivery.Repositories;

import com.fooddelivery.Entities.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
    @Query("Select ca from CustomerAddress ca where ca.isActive=true and ca.city=:city")
    CustomerAddress findByCity(@Param("city") String city);


}
