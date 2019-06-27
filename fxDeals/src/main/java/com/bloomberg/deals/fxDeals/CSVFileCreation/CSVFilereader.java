package com.bloomberg.deals.fxDeals.CSVFileCreation;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFilereader {

    public static List<FXDealReport> readFXDealReport(String fileName) {
        List<FXDealReport> fxReports = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                FXDealReport reportDetails = createFxDeal(attributes);
                fxReports.add(reportDetails);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return fxReports;
    }

    private static FXDealReport createFxDeal(String[] metadata) {
        String deal_unique_id = metadata[0];
        String ordering_currency_iso_code = metadata[1];
        String to_currency_iso_code = metadata[2];
        String timestamp = metadata[3];
        String deal_amount = metadata[4];

        return new FXDealReport(deal_unique_id, ordering_currency_iso_code, to_currency_iso_code, timestamp, deal_amount);
    }

}
