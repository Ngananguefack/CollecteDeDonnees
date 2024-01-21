package com.example.demo.Controller;

import com.example.demo.Entities.Cadre;
import com.example.demo.Repository.CadreRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.CadreService;
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
@RequestMapping("/api/cadres")
public class CadreController {

    private CadreRepository cadreRepository;

    @Autowired
    public CadreController(CadreService cadreService, CadreRepository cadreRepository) {

        this.cadreService = cadreService;
        this.cadreRepository = cadreRepository;
    }
    @Autowired
    private CadreService cadreService;

    @GetMapping
    public List<Cadre> getAllCadres() {
        return cadreService.getAllCadres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cadre> getCadreById(@PathVariable int id) {
        Optional<Cadre> cadre = cadreService.getCadreById(id);
        return cadre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cadre> saveCadre(@RequestBody Cadre cadre) {
        Cadre savedCadre = cadreService.saveCadre(cadre);
        return new ResponseEntity<>(savedCadre, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCadre(@PathVariable int id) {
        cadreService.deleteCadre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            cadreService.saveExcelData(cadreRepository, file.getInputStream());
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
            cadreService.savePdfData(cadreRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
