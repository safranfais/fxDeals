package com.bloomberg.deals.fxDeals.controllers;

import com.bloomberg.deals.fxDeals.CSVFileCreation.CSVUtils;
import com.bloomberg.deals.fxDeals.constants.ConstantsFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

@Controller
public class FxDealController {

    private static final Logger LOGGER = LogManager.getLogger(FxDealController.class);


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

            if (CSVUtils.fileType(file).equals(ConstantsFields.CSV)) {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream inputFileStream = new ByteArrayInputStream(bytes);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream));
                String line = "";
                while ((line = br.readLine()) != null) {
                    LOGGER.debug("Read data line : [ " + line + " ] ");
                }
                br.close();
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
