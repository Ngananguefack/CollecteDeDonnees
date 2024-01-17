package com.example.demo.Controller;

import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.PaysagesUrbainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paysagesUrbains")
@CrossOrigin(origins = "http://localhost:3000")
public class PaysagesUrbainsController {
    private PaysagesUrbainsRepository paysagesUrbainsRepository;

    @Autowired
    public PaysagesUrbainsController(PaysagesUrbainsService paysagesUrbainsService, PaysagesUrbainsRepository paysagesUrbainsRepository) {

        this.paysagesUrbainsService=paysagesUrbainsService;
        this.paysagesUrbainsRepository=paysagesUrbainsRepository;
    }

    @Autowired
    private PaysagesUrbainsService paysagesUrbainsService;

    @GetMapping
    public List<PaysagesUrbains> getAllPaysagesUrbains() {
        return paysagesUrbainsService.getAllPaysagesUrbains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaysagesUrbains> getPaysagesUrbainsById(@PathVariable int id) {
        Optional<PaysagesUrbains> paysagesUrbains = paysagesUrbainsService.getPaysagesUrbainsById(id);
        return paysagesUrbains.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PaysagesUrbains> savePaysagesUrbains(@RequestBody PaysagesUrbains paysagesUrbains) {
        PaysagesUrbains savedPaysagesUrbains = paysagesUrbainsService.savePaysagesUrbains(paysagesUrbains);
        return new ResponseEntity<>(savedPaysagesUrbains, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaysagesUrbains(@PathVariable int id) {
        paysagesUrbainsService.deletePaysagesUrbains(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            paysagesUrbainsService.saveExcelData(paysagesUrbainsRepository, file.getInputStream());
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
            paysagesUrbainsService.savePdfData(paysagesUrbainsRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
