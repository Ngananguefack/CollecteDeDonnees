package com.example.demo.Controller;

import com.example.demo.Entities.Senateur;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.SenateurRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.SenateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/senateurs")
@CrossOrigin(origins = "http://localhost:3000")

public class SenateurController {
    private SenateurRepository senateurRepository;

    @Autowired
    public SenateurController(SenateurService senateurService, SenateurRepository senateurRepository) {

        this.senateurService=senateurService;
        this.senateurRepository=senateurRepository;
    }


    @Autowired
    private SenateurService senateurService;

    @GetMapping
    public List<Senateur> getAllSenateurs() {
        return senateurService.getAllSenateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Senateur> getSenateurById(@PathVariable int id) {
        Optional<Senateur> senateur = senateurService.getSenateurById(id);
        return senateur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("senateur")
    public ResponseEntity<Senateur> saveSenateur(@RequestBody Senateur senateur) {
        Senateur savedSenateur = senateurService.saveSenateur(senateur);
        return new ResponseEntity<>(savedSenateur, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSenateur(@PathVariable int id) {
        senateurService.deleteSenateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            senateurService.saveExcelData(senateurRepository, file.getInputStream());
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
            senateurService.savePdfData(senateurRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
