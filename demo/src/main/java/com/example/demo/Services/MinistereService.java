package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.Ministere;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.MinistereRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

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
public class MinistereService {
    @Autowired
    private MinistereRepository ministereRepository;

    public List<Ministere> getAllMinisteres() {
        return ministereRepository.findAll();
    }

    public Optional<Ministere> getMinistereById(int id) {
        return ministereRepository.findById(id);
    }

    public Ministere saveMinistere(Ministere ministere) {
        return ministereRepository.save(ministere);
    }

    public void deleteMinistere(int id) {
        ministereRepository.deleteById(id);
    }

    public void saveExcelData(MinistereRepository ministereRepository, InputStream inputStream) throws IOException {
        Set<Ministere> ministeres = new HashSet<Ministere>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Ministere ministere= new Ministere();
                ministere.setDesignation(row.getCell(0).getStringCellValue());
                ministere.setSiteInternet(row.getCell(1).getStringCellValue());
                ministere.setLocalisation(row.getCell(2).getStringCellValue());
                ministere.setMinistere(row.getCell(3).getStringCellValue());
                ministere.setSecretariatEtat(row.getCell(5).getStringCellValue());
                ministeres.add(ministere);
            });

            ministereRepository.saveAll(ministeres);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(MinistereRepository ministereRepository, InputStream inputStream) throws IOException {
     Set<Ministere> ministeres = new HashSet<Ministere>();
        PdfDocument pdf= new PdfDocument(inputStream);
      //Create a PdfTableExtractor instance
      PdfTableExtractor extractor = new PdfTableExtractor(pdf);

      //Loop through the pages in the PDF
      for (int pageIndex = 0; pageIndex < pdf.getPages().getCount(); pageIndex++) {
        //Extract tables from the current page into a PdfTable array
        PdfTable[] tableLists = extractor.extractTable(pageIndex);
        
        //If any tables are found
        if (tableLists != null && tableLists.length > 0) {
            //Loop through the tables in the array
            for (PdfTable table : tableLists) {
                //Loop through the rows in the current table
                for (int i =1; i < table.getRowCount(); i++) {
                    //Loop through the columns in the current table
                    Ministere ministere= new Ministere();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        
                        ministere.setDesignation(table.getText(i, j));
                        ministere.setSiteInternet(table.getText(i, j+1));
                        ministere.setLocalisation(table.getText(i, j+2));
                        ministere.setMinistere(table.getText(i, j+3));
                        ministere.setSecretariatEtat(table.getText(i, j+4));
 
                        ministeres.add(ministere);
                }
            }


        }
      }
      ministereRepository.saveAll(ministeres);
    
        }
}
