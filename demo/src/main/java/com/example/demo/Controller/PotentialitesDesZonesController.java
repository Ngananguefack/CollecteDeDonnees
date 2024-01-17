package com.example.demo.Controller;

import com.example.demo.Entities.PotentialitesDesZones;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.PotentialitesDesZonesRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.PossibiliteDeZoneService;
import com.example.demo.Services.PotentialitesDesZonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/potentialites")
@CrossOrigin(origins = "http://localhost:3000")
public class PotentialitesDesZonesController {
    private PotentialitesDesZonesRepository potentialitesDesZonesRepository;

    @Autowired
    public PotentialitesDesZonesController(PotentialitesDesZonesService potentialitesDesZonesService, PotentialitesDesZonesRepository potentialitesDesZonesRepository) {

        this.potentialitesDesZonesService=potentialitesDesZonesService;
        this.potentialitesDesZonesRepository=potentialitesDesZonesRepository;
    }


    @Autowired
    private PotentialitesDesZonesService potentialitesDesZonesService;

    @GetMapping
    public List<PotentialitesDesZones> getAllPotentialitesDesZones() {
        return potentialitesDesZonesService.getAllPotentialitesDesZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PotentialitesDesZones> getPotentialitesDesZonesById(@PathVariable int id) {
        Optional<PotentialitesDesZones> potentialitesDesZones = potentialitesDesZonesService.getPotentialitesDesZonesById(id);
        return potentialitesDesZones.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PotentialitesDesZones> savePotentialitesDesZones(@RequestBody PotentialitesDesZones potentialitesDesZones) {
        PotentialitesDesZones savedPotentialitesDesZones = potentialitesDesZonesService.savePotentialitesDesZones(potentialitesDesZones);
        return new ResponseEntity<>(savedPotentialitesDesZones, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePotentialitesDesZones(@PathVariable int id) {
        potentialitesDesZonesService.deletePotentialitesDesZones(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            potentialitesDesZonesService.saveExcelData(potentialitesDesZonesRepository, file.getInputStream());
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
            potentialitesDesZonesService.savePdfData(potentialitesDesZonesRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
