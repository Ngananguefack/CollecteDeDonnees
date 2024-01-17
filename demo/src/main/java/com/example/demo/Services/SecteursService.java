package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Secteurs;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.SecteursRepository;
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
public class SecteursService {

    @Autowired
    private SecteursRepository secteursRepository;

    public List<Secteurs> getAllSecteurs() {
        return secteursRepository.findAll();
    }

    public Optional<Secteurs> getSecteursById(int id) {
        return secteursRepository.findById(id);
    }

    public Secteurs saveSecteurs(Secteurs secteurs) {
        return secteursRepository.save(secteurs);
    }

    public void deleteSecteurs(int id) {
        secteursRepository.deleteById(id);
    }

    public void saveExcelData(SecteursRepository secteursRepository, InputStream inputStream) throws IOException {
        Set<Secteurs> secteurs = new HashSet<Secteurs>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Secteurs secteurs1= new Secteurs();
                secteurs1.setLibelle(row.getCell(0).getStringCellValue());

                secteurs.add(secteurs1);
            });

            secteursRepository.saveAll(secteurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(SecteursRepository secteursRepository, InputStream inputStream) throws IOException {
     Set<Secteurs> secteurs = new HashSet<Secteurs>();
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
                    Secteurs secteurs1= new Secteurs();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                       
                        secteurs1.setLibelle(table.getText(i, j));
                        
                        secteurs.add(secteurs1);
                }
            }


        }
      }
      secteursRepository.saveAll(secteurs);
    
        }
}
