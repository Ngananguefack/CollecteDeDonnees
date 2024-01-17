package com.example.demo.Controller;

import com.example.demo.Entities.Zone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ZoneRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zones")
@CrossOrigin(origins = "http://localhost:3000")
public class ZoneController {
    private ZoneRepository zoneRepository;

    @Autowired
    public ZoneController(ZoneService zoneService, ZoneRepository zoneRepository) {

        this.zoneService=zoneService;
        this.zoneRepository=zoneRepository;
    }


    @Autowired
    private ZoneService zoneService;

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable int id) {
        Optional<Zone> zone = zoneService.getZoneById(id);
        return zone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/zone")
    public ResponseEntity<Zone> saveZone(@RequestBody Zone zone) {
        Zone savedZone = zoneService.saveZone(zone);
        return new ResponseEntity<>(savedZone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable int id) {
        zoneService.deleteZone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            zoneService.saveExcelData(zoneRepository, file.getInputStream());
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
            zoneService.savePdfData(zoneRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
