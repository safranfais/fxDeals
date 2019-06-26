package com.bloomberg.deals.fxDeals;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FxDealController {

    public static final String NAME = "Fx Deal File Uploading ";
    private static String UPLOADED_FOLDER = "C://temp//";

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("name", NAME);
        return "index";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("fxfile") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            file.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "Successfully uploaded Your File : [ " + file.getOriginalFilename() + "]");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message",
                    "Your file uploading failed : [ " + file.getOriginalFilename() + "]");
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }


    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
