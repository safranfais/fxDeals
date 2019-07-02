package com.bloomberg.deals.fxDeals.controllers;

import com.bloomberg.deals.fxDeals.entity.FXDeal;
import com.bloomberg.deals.fxDeals.entity.IFXDeal;
import com.bloomberg.deals.fxDeals.entity.InvalidFXDeal;
import com.bloomberg.deals.fxDeals.repository.FxDealRepository;
import com.bloomberg.deals.fxDeals.repository.InvalidFxDealRepository;
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
    private FxDealRepository fxDealRepository;

    @Autowired
    private InvalidFxDealRepository invalidFxDealRepository;


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

                List<FXDeal> fxDealData = new ArrayList<>();
                List<InvalidFXDeal> invalidFxDealData = new ArrayList<>();

                String fxData = "";
                while ((fxData = bufferedReader.readLine()) != null) {
                    LOGGER.debug("Read data line : [ " + fxData + " ] ");
                    IFXDeal result = ValidatingModel.ValidateObject(fxData);
                    if (result.getIsActive() == 1) {
                        fxDealData.add((FXDeal) result);
                    } else if (fxData.split(",")[0].toString().equals("Deal Unique Id")) {

                    } else {
                        result.setIsActive(1);
                        invalidFxDealData.add((InvalidFXDeal) result);
                    }
                }

                LOGGER.debug("Saving Valid Data to Database");
                fxDealRepository.saveAll(fxDealData);
                LOGGER.debug("Saving Invalid Data to Database");
                invalidFxDealRepository.saveAll(invalidFxDealData);
                LOGGER.debug("Valid and Invalid Saved Succesfully");

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
