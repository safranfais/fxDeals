package com.bloomberg.deals.fxDeals.entity;

public interface IFXDeal {

    int getIsActive();

    void setIsActive(int i);

    void setDeal_unique_id(String toString);

    void setOrdering_currency_iso_code(String toString);

    void setTo_currency_iso_code(String toString);

    void setTimestamp(String toString);

    void setDeal_amount(String toString);


    void setReference_source_name(String fileName);
}
