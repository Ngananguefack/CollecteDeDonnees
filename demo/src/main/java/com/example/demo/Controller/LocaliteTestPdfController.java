package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.ImportService;
import com.example.demo.Services.LocalisationServicePdf;

@RestController
@RequestMapping("/api/import/localites")
public class LocaliteTestPdfController {

    private final LocaliteRepository localiteRepository;
    private LocalisationServicePdf localizationServicePdf;
        @Autowired
    public LocaliteTestPdfController(LocalisationServicePdf localisationServicePdf, LocaliteRepository localiteRepository) {
        this.localizationServicePdf = localisationServicePdf;
        this.localiteRepository = localiteRepository;
    }

    @PostMapping("/pdf")
    public ResponseEntity<String> importPdf(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            localizationServicePdf.savePdfData(localiteRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing data: " + e.getMessage());
        }
    }


}
