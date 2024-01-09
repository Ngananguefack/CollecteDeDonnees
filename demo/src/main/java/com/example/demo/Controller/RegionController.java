package com.example.demo.Controller;

import com.example.demo.Entities.Region;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RegionRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private RegionRepository regionRepository;

    @Autowired
    public RegionController(RegionService regionService, RegionRepository regionRepository) {

        this.regionService= regionService;
        this.regionRepository=regionRepository;
    }


    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Region> getRegionByCode(@PathVariable int code) {
        Optional<Region> region = regionService.getRegionByCode(code);
        return region.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Region> saveRegion(@RequestBody Region region) {
        Region savedRegion = regionService.saveRegion(region);
        return new ResponseEntity<>(savedRegion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteRegion(@PathVariable int code) {
        regionService.deleteRegion(code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            regionService.saveExcelData(regionRepository, file.getInputStream());
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
            regionService.savePdfData(regionRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
