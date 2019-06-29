package com.bloomberg.deals.fxDeals.utils;

import com.bloomberg.deals.fxDeals.entity.FXDealDataWarehouse;

public class CSVFileReader {

//    public static List<FXDealDataWarehouse> readFXDealReport(String fileName) {
//        List<FXDealDataWarehouse> fxReports = new ArrayList<>();
//        Path pathToFile = Paths.get(fileName);
//
//        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
//
//            // read the first line from the text file
//            String line = br.readLine();
//            while (line != null) {
//                String[] attributes = line.split(",");
//                FXDealDataWarehouse reportDetails = createFxDeal(attributes);
//                fxReports.add(reportDetails);
//                line = br.readLine();
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        return fxReports;
//    }

    private static FXDealDataWarehouse createFxDealReportModel(String[] metadata) {
        String deal_unique_id = metadata[0];
        String ordering_currency_iso_code = metadata[1];
        String to_currency_iso_code = metadata[2];
        String timestamp = metadata[3];
        String deal_amount = metadata[4];

        return null;
//        return new FXDealDataWarehouse(deal_unique_id, ordering_currency_iso_code, to_currency_iso_code, timestamp, deal_amount);
    }

}
