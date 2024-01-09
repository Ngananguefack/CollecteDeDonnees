package com.example.demo.Controller;

import com.example.demo.Entities.RessourcesZone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RessourcesZoneRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.RessourcesZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ressourceszones")
public class RessourcesZoneController {
    private RessourcesZoneRepository ressourcesZoneRepository;

    @Autowired
    public RessourcesZoneController(RessourcesZoneService ressourcesZoneService, RessourcesZoneRepository ressourcesZoneRepository) {

        this.ressourcesZoneService=ressourcesZoneService;
        this.ressourcesZoneRepository=ressourcesZoneRepository;
    }


    @Autowired
    private RessourcesZoneService ressourcesZoneService;

    @GetMapping
    public List<RessourcesZone> getAllRessourcesZones() {
        return ressourcesZoneService.getAllRessourcesZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RessourcesZone> getRessourcesZoneById(@PathVariable int id) {
        Optional<RessourcesZone> ressourcesZone = ressourcesZoneService.getRessourcesZoneById(id);
        return ressourcesZone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RessourcesZone> saveRessourcesZone(@RequestBody RessourcesZone ressourcesZone) {
        RessourcesZone savedRessourcesZone = ressourcesZoneService.saveRessourcesZone(ressourcesZone);
        return new ResponseEntity<>(savedRessourcesZone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessourcesZone(@PathVariable int id) {
        ressourcesZoneService.deleteRessourcesZone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            ressourcesZoneService.saveExcelData(ressourcesZoneRepository, file.getInputStream());
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
            ressourcesZoneService.savePdfData(ressourcesZoneRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
