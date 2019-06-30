package com.bloomberg.deals.fxDeals.repository;

import com.bloomberg.deals.fxDeals.entity.InvalidFXDealDataWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidFxDealRepository extends JpaRepository<InvalidFXDealDataWarehouse,String> {
}
