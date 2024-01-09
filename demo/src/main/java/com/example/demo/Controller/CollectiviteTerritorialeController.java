package com.example.demo.Controller;

import com.example.demo.Entities.CollectiviteTerritoriale;
//import com.example.demo.Service.CollectiviteTerritorialeService;
import com.example.demo.Repository.ChefferieRepository;
import com.example.demo.Repository.CollectiviteTerritorialeRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.CollectiviteTerritorialeService;
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
@RequestMapping("/collectivites")
public class CollectiviteTerritorialeController {
    private CollectiviteTerritorialeRepository collectiviteTerritorialeRepository;

    @Autowired
    public CollectiviteTerritorialeController(CollectiviteTerritorialeService collectiviteTerritorialeService, CollectiviteTerritorialeRepository collectiviteTerritorialeRepository) {

        this.collectiviteTerritorialeService = collectiviteTerritorialeService;
        this.collectiviteTerritorialeRepository = collectiviteTerritorialeRepository;
    }

    @Autowired
    private CollectiviteTerritorialeService collectiviteTerritorialeService;

    @GetMapping
    public List<CollectiviteTerritoriale> getAllCollectivitesTerritoriales() {
        return collectiviteTerritorialeService.getAllCollectivitesTerritoriales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectiviteTerritoriale> getCollectiviteTerritorialeById(@PathVariable int id) {
        Optional<CollectiviteTerritoriale> collectiviteTerritoriale = collectiviteTerritorialeService.getCollectiviteTerritorialeById(id);
        return collectiviteTerritoriale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CollectiviteTerritoriale> saveCollectiviteTerritoriale(@RequestBody CollectiviteTerritoriale collectiviteTerritoriale) {
        CollectiviteTerritoriale savedCollectiviteTerritoriale = collectiviteTerritorialeService.saveCollectiviteTerritoriale(collectiviteTerritoriale);
        return new ResponseEntity<>(savedCollectiviteTerritoriale, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectiviteTerritoriale(@PathVariable int id) {
        collectiviteTerritorialeService.deleteCollectiviteTerritoriale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            collectiviteTerritorialeService.saveExcelData(collectiviteTerritorialeRepository, file.getInputStream());
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
            collectiviteTerritorialeService.savePdfData(collectiviteTerritorialeRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
