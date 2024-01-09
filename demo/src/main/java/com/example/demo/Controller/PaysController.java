package com.example.demo.Controller;

import com.example.demo.Entities.Pays;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pays")
public class PaysController {
    private PaysRepository paysRepository;

    @Autowired
    public PaysController(PaysService paysService, PaysRepository paysRepository) {

        this.paysService=paysService;
        this.paysRepository=paysRepository;
    }


    @Autowired
    private PaysService paysService;

    @GetMapping
    public List<Pays> getAllPays() {
        return paysService.getAllPays();
    }

    @GetMapping("/{codePays}")
    public ResponseEntity<Pays> getPaysByCode(@PathVariable int codePays) {
        Optional<Pays> pays = paysService.getPaysByCode(codePays);
        return pays.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Pays> savePays(@RequestBody Pays pays) {
        Pays savedPays = paysService.savePays(pays);
        return new ResponseEntity<>(savedPays, HttpStatus.CREATED);
    }

    @DeleteMapping("/{codePays}")
    public ResponseEntity<Void> deletePays(@PathVariable int codePays) {
        paysService.deletePays(codePays);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            paysService.saveExcelData(paysRepository, file.getInputStream());
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
            paysService.savePdfData(paysRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
