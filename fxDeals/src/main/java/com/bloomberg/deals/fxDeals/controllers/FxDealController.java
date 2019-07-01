package com.bloomberg.deals.fxDeals.controllers;

import com.bloomberg.deals.fxDeals.models.FxDealDataWarehouseModel;
import com.bloomberg.deals.fxDeals.repository.FxDealRepository;
import com.bloomberg.deals.fxDeals.utils.CSVUtils;
import com.bloomberg.deals.fxDeals.utils.Constants;
import com.bloomberg.deals.fxDeals.utils.ValidatingModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FxDealController {

    private static final Logger LOGGER = LogManager.getLogger(FxDealController.class);

    @Autowired
    private FxDealRepository fxDealRepository ;


    public static final String NAME = "Fx Deal File Uploading ";

    @GetMapping("/")
    public String homePage(Model model) {
        LOGGER.debug("Executing the index.html to pass the message as a : " + NAME);
        model.addAttribute("name", NAME);
        return "index";
    }

    @PostMapping("/upload")
    public String fxFileUpload(@RequestParam("fxfile") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            LOGGER.debug("There is no file was uploaded!!!");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            LOGGER.debug("Uploaded file [ " + file.getOriginalFilename() + " ] is start to read");

            if (CSVUtils.fileType(file).equals(Constants.CSV)) {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream inputFileStream = new ByteArrayInputStream(bytes);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputFileStream));

                List<FxDealDataWarehouseModel> fxDealDataWarehouseModels = new ArrayList<>();
                List<FxDealDataWarehouseModel> invalidFxDealDataWarehouseModels = new ArrayList<>();

                String fxData = "";
                while ((fxData = bufferedReader.readLine()) != null) {
                    LOGGER.debug("Read data line : [ " + fxData + " ] ");
                    FxDealDataWarehouseModel result = ValidatingModel.ValidateObject(fxData);
                    if (result.getIsActive() == 1) {
                        fxDealDataWarehouseModels.add(result);
                    } else if (fxData.split(",")[0].toString().equals("Deal Unique Id")) {

                    } else {
                        result.setIsActive(1);
                        invalidFxDealDataWarehouseModels.add(result);
                    }
                }

//                fxDealRepository.save(fxDealDataWarehouseModels);

                bufferedReader.close();
                redirectAttributes.addFlashAttribute("message", "Successfully uploaded Your File : [ " + file.getOriginalFilename() + "]");
            } else {
                LOGGER.debug("Do not process the none csv file format [ " + file.getOriginalFilename() + " ] ");
                redirectAttributes.addFlashAttribute("message", "Please Upload the csv file format : [ " + file.getOriginalFilename() + "]");
            }

        } catch (IOException e) {
            LOGGER.error("File Uploading Error : " + e);
            redirectAttributes.addFlashAttribute("message", "Your file uploading failed : [ " + file.getOriginalFilename() + "]");
        }

        return "redirect:/uploadStatus";
    }



    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
