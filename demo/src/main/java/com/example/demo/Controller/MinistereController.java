package com.example.demo.Controller;

import com.example.demo.Entities.Ministere;
import com.example.demo.Repository.MinistereRepository;
import com.example.demo.Services.MinistereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ministeres")
@CrossOrigin(origins = "http://localhost:3000")
public class MinistereController {
    private MinistereRepository ministereRepository;

    @Autowired
    public MinistereController(MinistereService ministereService, MinistereRepository ministereRepository) {

        this.ministereService= ministereService;
        this.ministereRepository=ministereRepository;
    }

    @Autowired
    private MinistereService ministereService;

    @GetMapping
    public List<Ministere> getAllMinisteres() {
        return ministereService.getAllMinisteres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ministere> getMinistereById(@PathVariable int id) {
        Optional<Ministere> ministere = ministereService.getMinistereById(id);
        return ministere.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ministere> saveMinistere(@RequestBody Ministere ministere) {
        Ministere savedMinistere = ministereService.saveMinistere(ministere);
        return new ResponseEntity<>(savedMinistere, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMinistere(@PathVariable int id) {
        ministereService.deleteMinistere(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            ministereService.saveExcelData(ministereRepository, file.getInputStream());
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
            ministereService.savePdfData(ministereRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
