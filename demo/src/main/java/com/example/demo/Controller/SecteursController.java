package com.example.demo.Controller;

import com.example.demo.Entities.Secteurs;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.SecteursRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.SecteursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/secteurs")
@CrossOrigin(origins = "http://localhost:3000")
public class SecteursController {
    private SecteursRepository secteursRepository;

    @Autowired
    public SecteursController(SecteursService secteursService, SecteursRepository secteursRepository) {

        this.secteursService=secteursService;
        this.secteursRepository=secteursRepository;
    }


    @Autowired
    private SecteursService secteursService;

    @GetMapping
    public List<Secteurs> getAllSecteurs() {
        return secteursService.getAllSecteurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secteurs> getSecteursById(@PathVariable int id) {
        Optional<Secteurs> secteurs = secteursService.getSecteursById(id);
        return secteurs.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Secteurs> saveSecteurs(@RequestBody Secteurs secteurs) {
        Secteurs savedSecteurs = secteursService.saveSecteurs(secteurs);
        return new ResponseEntity<>(savedSecteurs, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecteurs(@PathVariable int id) {
        secteursService.deleteSecteurs(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            secteursService.saveExcelData(secteursRepository, file.getInputStream());
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
            secteursService.savePdfData(secteursRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
