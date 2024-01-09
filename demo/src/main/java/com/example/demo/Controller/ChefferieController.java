package com.example.demo.Controller;

import com.example.demo.Entities.Chefferie;
//import com.example.demo.Service.ChefferieService;
import com.example.demo.Repository.ChefferieRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Services.ChefferieService;
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
@RequestMapping("/chefferies")
public class ChefferieController {

    private ChefferieRepository chefferieRepository;
    @Autowired
    public ChefferieController(ChefferieService chefferieService, ChefferieRepository chefferieRepository) {

        this.chefferieService = chefferieService;
        this.chefferieRepository = chefferieRepository;
    }
    @Autowired
    private ChefferieService chefferieService;

    @GetMapping
    public List<Chefferie> getAllChefferies() {
        return chefferieService.getAllChefferies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chefferie> getChefferieById(@PathVariable int id) {
        Optional<Chefferie> chefferie = chefferieService.getChefferieById(id);
        return chefferie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Chefferie> saveChefferie(@RequestBody Chefferie chefferie) {
        Chefferie savedChefferie = chefferieService.saveChefferie(chefferie);
        return new ResponseEntity<>(savedChefferie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChefferie(@PathVariable int id) {
        chefferieService.deleteChefferie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            chefferieService.saveExcelData(chefferieRepository, file.getInputStream());
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
            chefferieService.savePdfData(chefferieRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
