package com.bloomberg.deals.fxDeals.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FXDeal {

    @Id
    private String deal_unique_id;
    private String ordering_currency_iso_code;
    private String to_currency_iso_code;
    private String timestamp;
    private String deal_amount;
    int isActive;

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getDeal_unique_id() {
        return deal_unique_id;
    }

    public void setDeal_unique_id(String deal_unique_id) {
        this.deal_unique_id = deal_unique_id;
    }

    public String getOrdering_currency_iso_code() {
        return ordering_currency_iso_code;
    }

    public void setOrdering_currency_iso_code(String ordering_currency_iso_code) {
        this.ordering_currency_iso_code = ordering_currency_iso_code;
    }

    public String getTo_currency_iso_code() {
        return to_currency_iso_code;
    }

    public void setTo_currency_iso_code(String to_currency_iso_code) {
        this.to_currency_iso_code = to_currency_iso_code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeal_amount() {
        return deal_amount;
    }

    public void setDeal_amount(String deal_amount) {
        this.deal_amount = deal_amount;
    }
}
