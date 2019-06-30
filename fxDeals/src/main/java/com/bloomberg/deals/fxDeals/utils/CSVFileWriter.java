package com.bloomberg.deals.fxDeals.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVFileWriter {
    private static final char DEFAULT_SEPARATOR = ',';

    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\Project\\_temp_file\\fxdeal.csv";
        FileWriter writer = new FileWriter(csvFile);

        CSVUtils.writeLine(writer, Arrays.asList("Deal Unique Id", "Ordering Currency ISO Code", "To Currency ISO Code", "Timestamp", "Deal Amount Ordering Currency"), DEFAULT_SEPARATOR, ' ');

        for (int i = 1; i < 100000; i++) {
            if(i%100==0){
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, "$", "", "error date", ""), DEFAULT_SEPARATOR, ' ');
            }else if (i >= 0 && i <= 10500) {
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, Currency.getInstance("INR").getCurrencyCode(), Currency.getInstance("USD").getCurrencyCode(), new Date().getTime(), "40"), DEFAULT_SEPARATOR, ' ');
            } else if (i >= 10501 && i <= 35000) {
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, Currency.getInstance("RUB").getCurrencyCode(), Currency.getInstance("USD").getCurrencyCode(), new Date().getTime(), "55"), DEFAULT_SEPARATOR, ' ');
            } else if (i >= 35001 && i <= 57000) {
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, Currency.getInstance("CNY").getCurrencyCode(), Currency.getInstance("USD").getCurrencyCode(), new Date().getTime(), "70"), DEFAULT_SEPARATOR, ' ');
            } else if (i >= 57001 && i <= 83000) {
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, Currency.getInstance("GBP").getCurrencyCode(), Currency.getInstance("USD").getCurrencyCode(), new Date().getTime(), "65"), DEFAULT_SEPARATOR, ' ');
            } else {
                CSVUtils.writeLine(writer, Arrays.asList("FX_" + i, Currency.getInstance("LKR").getCurrencyCode(), Currency.getInstance("USD").getCurrencyCode(), new Date().getTime(), "65"), DEFAULT_SEPARATOR, ' ');
            }
        }
        writer.flush();
        writer.close();

    }

}
