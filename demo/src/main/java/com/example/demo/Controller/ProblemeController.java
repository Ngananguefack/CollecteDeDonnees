package com.example.demo.Controller;

import com.example.demo.Entities.Probleme;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ProblemeRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.ProblemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/problemes")
public class ProblemeController {
    private ProblemeRepository problemeRepository;

    @Autowired
    public ProblemeController(ProblemeService problemeService, ProblemeRepository problemeRepository) {

        this.problemeService=problemeService;
        this.problemeRepository=problemeRepository;
    }


    @Autowired
    private ProblemeService problemeService;

    @GetMapping
    public List<Probleme> getAllProblemes() {
        return problemeService.getAllProblemes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Probleme> getProblemeById(@PathVariable int id) {
        Optional<Probleme> probleme = problemeService.getProblemeById(id);
        return probleme.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Probleme> saveProbleme(@RequestBody Probleme probleme) {
        Probleme savedProbleme = problemeService.saveProbleme(probleme);
        return new ResponseEntity<>(savedProbleme, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProbleme(@PathVariable int id) {
        problemeService.deleteProbleme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            problemeService.saveExcelData(problemeRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing data: " + e.getMessage());
        }
    }
    
    @PostMapping("/import/pdf")
    public ResponseEntity<String> importPdf(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            problemeService.savePdfData(problemeRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
