package com.example.demo.Controller;

import com.example.demo.Entities.Gouverneur;
import com.example.demo.Repository.GouverneurRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.GouverneurService;
import com.example.demo.Services.LocaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gouverneurs")
@CrossOrigin(origins = "http://localhost:3000")
public class GouverneurController {
    private GouverneurRepository gouverneurRepository;

    @Autowired
    public GouverneurController(GouverneurService gouverneurService, GouverneurRepository gouverneurRepository) {

        this.gouverneurService= gouverneurService;
        this.gouverneurRepository=gouverneurRepository;
    }

    @Autowired
    private GouverneurService gouverneurService;

    @GetMapping
    public List<Gouverneur> getAllGouverneurs() {
        return gouverneurService.getAllGouverneurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gouverneur> getGouverneurById(@PathVariable int id) {
        Optional<Gouverneur> gouverneur = gouverneurService.getGouverneurById(id);
        return gouverneur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Gouverneur> saveGouverneur(@RequestBody Gouverneur gouverneur) {
        Gouverneur savedGouverneur = gouverneurService.saveGouverneur(gouverneur);
        return new ResponseEntity<>(savedGouverneur, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGouverneur(@PathVariable int id) {
        gouverneurService.deleteGouverneur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            gouverneurService.saveExcelData(gouverneurRepository, file.getInputStream());
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
            gouverneurService.savePdfData(gouverneurRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
