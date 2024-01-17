package com.example.demo.Controller;

import com.example.demo.Entities.PossibiliteDeZone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.PossibiliteDeZoneRepository;
import com.example.demo.Repository.PotentialitesDesZonesRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.PossibiliteDeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/possibilitesdezone")
@CrossOrigin(origins = "http://localhost:3000")
public class PossibiliteDeZoneController {
    private PossibiliteDeZoneRepository possibiliteDeZoneRepository;

    @Autowired
    public PossibiliteDeZoneController(PossibiliteDeZoneService possibiliteDeZoneService, PossibiliteDeZoneRepository possibiliteDeZoneRepository) {

        this.possibiliteDeZoneService=possibiliteDeZoneService;
        this.possibiliteDeZoneRepository=possibiliteDeZoneRepository;
    }


    @Autowired
    private PossibiliteDeZoneService possibiliteDeZoneService;

    @GetMapping
    public List<PossibiliteDeZone> getAllPossibilitesDeZone() {
        return possibiliteDeZoneService.getAllPossibilitesDeZone();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PossibiliteDeZone> getPossibiliteDeZoneById(@PathVariable int id) {
        Optional<PossibiliteDeZone> possibiliteDeZone = possibiliteDeZoneService.getPossibiliteDeZoneById(id);
        return possibiliteDeZone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PossibiliteDeZone> savePossibiliteDeZone(@RequestBody PossibiliteDeZone possibiliteDeZone) {
        PossibiliteDeZone savedPossibiliteDeZone = possibiliteDeZoneService.savePossibiliteDeZone(possibiliteDeZone);
        return new ResponseEntity<>(savedPossibiliteDeZone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePossibiliteDeZone(@PathVariable int id) {
        possibiliteDeZoneService.deletePossibiliteDeZone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            possibiliteDeZoneService.saveExcelData(possibiliteDeZoneRepository, file.getInputStream());
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
            possibiliteDeZoneService.savePdfData(possibiliteDeZoneRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
