package com.bloomberg.deals.fxDeals.repository;

import com.bloomberg.deals.fxDeals.entity.impl.FXDeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends CrudRepository<FXDeal,String> {
}
