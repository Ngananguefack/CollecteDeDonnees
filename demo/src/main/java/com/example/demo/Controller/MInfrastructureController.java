package com.example.demo.Controller;

import com.example.demo.Entities.MInfrastructure;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.MInfrastructureRepository;
import com.example.demo.Services.LocaliteService;
import com.example.demo.Services.MInfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/minfrastructures")
public class MInfrastructureController {
    private MInfrastructureRepository mInfrastructureRepository;

    @Autowired
    public MInfrastructureController(MInfrastructureService mInfrastructureService, MInfrastructureRepository mInfrastructureRepository) {

        this.mInfrastructureService= mInfrastructureService;
        this.mInfrastructureRepository= mInfrastructureRepository;
    }


    @Autowired
    private MInfrastructureService mInfrastructureService;

    @GetMapping
    public List<MInfrastructure> getAllMInfrastructures() {
        return mInfrastructureService.getAllMInfrastructures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MInfrastructure> getMInfrastructureById(@PathVariable int id) {
        Optional<MInfrastructure> mInfrastructure = mInfrastructureService.getMInfrastructureById(id);
        return mInfrastructure.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MInfrastructure> saveMInfrastructure(@RequestBody MInfrastructure mInfrastructure) {
        MInfrastructure savedMInfrastructure = mInfrastructureService.saveMInfrastructure(mInfrastructure);
        return new ResponseEntity<>(savedMInfrastructure, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMInfrastructure(@PathVariable int id) {
        mInfrastructureService.deleteMInfrastructure(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            mInfrastructureService.saveExcelData(mInfrastructureRepository, file.getInputStream());
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
            mInfrastructureService.savePdfData(mInfrastructureRepository, file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }

    }
}
