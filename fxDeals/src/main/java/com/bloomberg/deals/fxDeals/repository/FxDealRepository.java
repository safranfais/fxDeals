package com.bloomberg.deals.fxDeals.repository;

import com.bloomberg.deals.fxDeals.entity.FXDealDataWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FxDealRepository extends JpaRepository<FXDealDataWarehouse,String> {
}
