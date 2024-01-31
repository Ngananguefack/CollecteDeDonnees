package com.example.demo.Controller;

import com.example.demo.Entities.Momo;
import com.example.demo.Services.MomoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/momos")
@CrossOrigin(origins = "http://localhost:3000")
public class MomoController {

    private final MomoService momoService;

    @Autowired
    public MomoController(MomoService momoService) {
        this.momoService = momoService;
    }

    @GetMapping
    public List<Momo> getAllMomos() {
        return momoService.getAllMomos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Momo> getMomoById(@PathVariable int id) {
        Optional<Momo> momo = momoService.getMomoById(id);
        return momo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Momo> saveMomo(@RequestBody Momo momo) {
        Momo savedMomo = momoService.saveMomo(momo);
        return new ResponseEntity<>(savedMomo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMomo(@PathVariable int id) {
        momoService.deleteMomo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import/excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            momoService.saveExcelData(file.getInputStream());
            return ResponseEntity.ok("Data imported successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing data: " + e.getMessage());
        }
    }

    // Add other import methods if needed (e.g., importPdf)

}
