package com.example.demo.Controller;

import com.example.demo.Entities.Infrastructure;
import com.example.demo.Repository.InfrastructureRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.InfrastructureService;
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
@RequestMapping("/infrastructures")
public class InfrastructureController {
    private InfrastructureRepository infrastructureRepository;

    @Autowired
    public InfrastructureController(InfrastructureService infrastructureService, InfrastructureRepository infrastructureRepository) {

        this.infrastructureService=infrastructureService;
        this.infrastructureRepository=infrastructureRepository;
    }


    @Autowired
    private InfrastructureService infrastructureService;

    @GetMapping
    public List<Infrastructure> getAllInfrastructures() {
        return infrastructureService.getAllInfrastructures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Infrastructure> getInfrastructureById(@PathVariable int id) {
        Optional<Infrastructure> infrastructure = infrastructureService.getInfrastructureById(id);
        return infrastructure.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Infrastructure> saveInfrastructure(@RequestBody Infrastructure infrastructure) {
        Infrastructure savedInfrastructure = infrastructureService.saveInfrastructure(infrastructure);
        return new ResponseEntity<>(savedInfrastructure, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfrastructure(@PathVariable int id) {
        infrastructureService.deleteInfrastructure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            infrastructureService.saveExcelData(infrastructureRepository, file.getInputStream());
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
            infrastructureService.savePdfData(infrastructureRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
