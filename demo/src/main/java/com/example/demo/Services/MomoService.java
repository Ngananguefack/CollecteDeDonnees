package com.example.demo.Services;

import com.example.demo.Entities.Momo;
import com.example.demo.Repository.MomoRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MomoService {

    @Autowired
    private MomoRepository momoRepository;

    public List<Momo> getAllMomos() {
        return momoRepository.findAll();
    }

    public Optional<Momo> getMomoById(int id) {
        return momoRepository.findById(id);
    }

    public Momo saveMomo(Momo momo) {
        return momoRepository.save(momo);
    }

    public void deleteMomo(int id) {
        momoRepository.deleteById(id);
    }

    public void saveExcelData(InputStream inputStream) throws IOException {
        Set<Momo> cadres = new HashSet<>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Row headerRow = workbook.getSheetAt(0).getRow(0); // Assuming the header is in the first row

            workbook.getSheetAt(0).forEach(row -> {
                if (row.getRowNum() != 0) { // Skip the header row
                    Momo cadre = new Momo();

                    cadre.setNumeroEnregistrement((int) row.getCell(0).getNumericCellValue());
                    cadre.setIdCadre((int) row.getCell(1).getNumericCellValue());
                    cadre.setIdSecteur((int) row.getCell(2).getNumericCellValue());
                    cadre.setIdChapitre((int) row.getCell(3).getNumericCellValue());
                    cadre.setIdProgramme((int) row.getCell(4).getNumericCellValue());
                    cadre.setIdAction((int) row.getCell(5).getNumericCellValue());
                    cadre.setIdActivite((int) row.getCell(6).getNumericCellValue());
                    cadre.setCadre(row.getCell(7).getStringCellValue());
                    cadre.setCNiveau((int) row.getCell(8).getNumericCellValue());
                    cadre.setAccessible((int) row.getCell(9).getNumericCellValue());

                    cadres.add(cadre);
                }
            });

            momoRepository.saveAll(cadres);
        }
    }
}
