package com.bloomberg.deals.fxDeals.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Currency;

public class ValidatingModel {

    private static final Logger LOGGER = LogManager.getLogger(ValidatingModel.class);

    boolean validatingDealId(String fxDealId) {
        LOGGER.debug("checking fx deal id : " + fxDealId);
        try {
            if (fxDealId.substring(0, 3).equals(Constants.FX_ID)) {
                LOGGER.debug("fx deal id first part is valid : " + fxDealId.substring(0, 3));

                Double.parseDouble(fxDealId.substring(3));
                LOGGER.debug("fx deal id second part is valid : " + fxDealId.substring(3));

                return true;
            } else {
                LOGGER.debug("fx deal id first part is invalid : " + fxDealId.substring(0, 3));

                Double.parseDouble(fxDealId.substring(3));
                LOGGER.debug("fx deal id second part is valid : " + fxDealId.substring(3));

                return false;
            }

        } catch (NumberFormatException e) {
            LOGGER.debug("fx deal id second part is invalid : " + fxDealId.substring(3));
            System.out.println("error "+e);
            return false;
        }
    }

    boolean validatingISOCurrencyCode(String currentIsoCode){
        LOGGER.debug("checking ISO Currency code : " + currentIsoCode);
        try{
            Currency.getInstance(currentIsoCode).getCurrencyCode();
            LOGGER.debug("ISO Currency code is Valid : " + currentIsoCode);
            return true;
        }catch(IllegalArgumentException e){
            LOGGER.debug("ISO Currency code is Invalid : " + currentIsoCode);
            return false;
        }

    }

    boolean validatingDateFormat(){
        return false;
    }

    boolean validatingDealAmount(String dealAmount){
        try{
            Double.parseDouble(dealAmount);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
