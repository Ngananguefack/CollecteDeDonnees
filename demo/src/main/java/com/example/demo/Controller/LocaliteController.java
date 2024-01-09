package com.example.demo.Controller;

import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.ImportService;
import com.example.demo.Services.LocalisationServicePdf;
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
@RequestMapping("/api/localites")
@CrossOrigin(origins = "http://localhost:3000")
public class LocaliteController {
    private LocaliteRepository localiteRepository;

    private LocaliteService localiteService;
    @Autowired
    public LocaliteController(LocaliteService localiteService, LocaliteRepository localiteRepository) {

            this.localiteService = localiteService;
            this.localiteRepository = localiteRepository;
    }

    @GetMapping
    public List<Localite> getAllLocalites() {
        return localiteService.getAllLocalites();
    }

    @GetMapping("/{codeLocalite}")
    public ResponseEntity<Localite> getLocaliteByCode(@PathVariable int codeLocalite) {
        Optional<Localite> localite = localiteService.getLocaliteByCode(codeLocalite);
        return localite.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/localite")
    public ResponseEntity<Localite> saveLocalite(@RequestBody Localite localite) {
        Localite savedLocalite = localiteService.saveLocalite(localite);
        return new ResponseEntity<>(savedLocalite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{codeLocalite}")
    public ResponseEntity<Void> deleteLocalite(@PathVariable int codeLocalite) {
        localiteService.deleteLocalite(codeLocalite);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            localiteService.saveExcelData(localiteRepository, file.getInputStream());
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
            localiteService.savePdfData(localiteRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing data: " + e.getMessage());
        }


}

}
