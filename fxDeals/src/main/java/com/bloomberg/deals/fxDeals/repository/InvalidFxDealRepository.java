package com.bloomberg.deals.fxDeals.repository;

import com.bloomberg.deals.fxDeals.entity.InvalidFXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidFxDealRepository extends JpaRepository<InvalidFXDeal,String> {
}
