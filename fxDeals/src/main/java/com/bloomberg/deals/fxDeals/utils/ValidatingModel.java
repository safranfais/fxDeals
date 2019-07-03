package com.bloomberg.deals.fxDeals.utils;

import com.bloomberg.deals.fxDeals.entity.impl.FXDeal;
import com.bloomberg.deals.fxDeals.entity.IFXDeal;
import com.bloomberg.deals.fxDeals.entity.impl.InvalidFXDeal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ValidatingModel {

    private static final Logger LOGGER = LogManager.getLogger(ValidatingModel.class);

    public static IFXDeal ValidateObject(String fxDealData , String fileName) {

        ValidatingModel validate = new ValidatingModel();
        IFXDeal fxDealModel =null ;

        String ar[] = fxDealData.split(",");

        if (ar.length > 4 && validate.validatingDealId(ar[0].toString()) && validate.validatingISOCurrencyCode(ar[1].toString()) &&
                validate.validatingISOCurrencyCode(ar[2].toString()) && validate.validatingDateFormat(ar[3].toString()) && validate.validatingDealAmount(ar[4].toString())) {
            LOGGER.debug("Valid Data add to the FxDealDataWarehouseModel");
            fxDealModel = new FXDeal();
            fxDealModel.setDeal_unique_id(ar[0].toString());
            fxDealModel.setOrdering_currency_iso_code(ar[1].toString());
            fxDealModel.setTo_currency_iso_code(ar[2].toString());
            fxDealModel.setTimestamp(ar[3].toString());
            fxDealModel.setDeal_amount(ar[4].toString());
            fxDealModel.setReference_source_name(fileName);
            fxDealModel.setIsActive(1);

        } else if (ar.length < 5) {
            fxDealModel = new InvalidFXDeal();

            LOGGER.debug("Invalid Data add to the FxDealDataWarehouseModel, missing and empty data to add the 'null' value ");
            ArrayList<String> list = new ArrayList( Arrays.asList( ar ) );
            for(int i = (5 - list.size()) ; i > 0 ; i--){
                list.add("null");
            }
            ar = list.stream().toArray(String[]::new);

            fxDealModel.setDeal_unique_id(ar[0].toString().equals("") ? "null" : ar[0].toString());
            fxDealModel.setOrdering_currency_iso_code(ar[1].toString().equals("") ? "null" : ar[1].toString());
            fxDealModel.setTo_currency_iso_code(ar[2].toString().equals("") ? "null" : ar[2].toString());
            fxDealModel.setTimestamp(ar[3].toString().equals("") ? "null" : ar[3].toString());
            fxDealModel.setDeal_amount(ar[4].toString().equals("") ? "null" : ar[4].toString());
            fxDealModel.setReference_source_name(fileName);
            fxDealModel.setIsActive(0);

        } else {
            fxDealModel = new InvalidFXDeal();
            LOGGER.debug("Invalid Data add to the FxDealDataWarehouseModel and add the 'null' value to empty values");
            fxDealModel.setDeal_unique_id(ar[0].toString().equals("") ? "null" : ar[0].toString());
            fxDealModel.setOrdering_currency_iso_code(ar[1].toString().equals("") ? "null" : ar[1].toString());
            fxDealModel.setTo_currency_iso_code(ar[2].toString().equals("") ? "null" : ar[2].toString());
            fxDealModel.setTimestamp(ar[3].toString().equals("") ? "null" : ar[3].toString());
            fxDealModel.setDeal_amount(ar[4].toString().equals("") ? "null" : ar[4].toString());
            fxDealModel.setReference_source_name(fileName);
            fxDealModel.setIsActive(0);
        }
        return fxDealModel;
    }

    boolean validatingDealId(String fxDealId) {
        String fx_deal_id1 = fxDealId.substring(0, 3);
        String fx_deal_id2 = fxDealId.substring(3);
        LOGGER.debug("checking fx deal id : " + fxDealId);
        try {
            if (fx_deal_id1.equals(Constants.FX_ID)) {
                LOGGER.debug("fx deal id first part is valid : " + fx_deal_id1);

                Double.parseDouble(fx_deal_id2);
                LOGGER.debug("fx deal id second part is valid : " + fx_deal_id2);

                return true;
            } else {
                LOGGER.debug("fx deal id first part is invalid : " + fx_deal_id1);

                Double.parseDouble(fx_deal_id2);
                LOGGER.debug("fx deal id second part is valid : " + fx_deal_id2);

                return false;
            }

        } catch (NumberFormatException e) {
            LOGGER.error("fx deal id second part is invalid : " + fxDealId.substring(3));
            return false;
        }
    }

    boolean validatingISOCurrencyCode(String currentIsoCode) {
        LOGGER.debug("checking ISO Currency code : " + currentIsoCode);
        try {
            Currency.getInstance(currentIsoCode).getCurrencyCode();
            LOGGER.debug("ISO Currency code is Valid : " + currentIsoCode);
            return true;
        } catch (IllegalArgumentException e) {
            LOGGER.error("ISO Currency code is Invalid : " + currentIsoCode);
            return false;
        }

    }

    boolean validatingDateFormat(String timestamp) {

        try {
            Date date = new Date(Long.parseLong(timestamp));
            LOGGER.debug("Timestamp is Valid : " + date.toString());
            return true;
        } catch (NumberFormatException e) {
            LOGGER.error("Timestamp is Invalid : [ " + timestamp + " ]");
            return false;
        }

    }

    boolean validatingDealAmount(String dealAmount) {
        try {
            Double.parseDouble(dealAmount);
            LOGGER.debug("Deal amount is Valid : " + dealAmount);
            return true;
        } catch (NumberFormatException e) {
            LOGGER.error("Deal amount is Invalid : " + dealAmount);
            return false;
        }
    }



}
