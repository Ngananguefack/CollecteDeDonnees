package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.Senateur;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.SenateurRepository;
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
public class SenateurService {

    @Autowired
    private SenateurRepository senateurRepository;

    public List<Senateur> getAllSenateurs() {
        return senateurRepository.findAll();
    }

    public Optional<Senateur> getSenateurById(int id) {
        return senateurRepository.findById(id);
    }

    public Senateur saveSenateur(Senateur senateur) {
        return senateurRepository.save(senateur);
    }

    public void deleteSenateur(int id) {
        senateurRepository.deleteById(id);
    }

    public void saveExcelData(SenateurRepository secteursRepository, InputStream inputStream) throws IOException {
        Set<Senateur> senateurs = new HashSet<Senateur>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Senateur senateur= new Senateur();
                senateur.setQualite(row.getCell(0).getStringCellValue());
                senateur.setNom(row.getCell(1).getStringCellValue());
                senateur.setPrenom(row.getCell(2).getStringCellValue());
                senateur.setMandat(row.getCell(3).getStringCellValue());
                senateur.setElu_Nomme(row.getCell(4).getStringCellValue());

                senateurs.add(senateur);
            });

            senateurRepository.saveAll(senateurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(SenateurRepository senateurRepository, InputStream inputStream) throws IOException {
     Set<Senateur> senateurs = new HashSet<Senateur>();
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
                for (int i = 1; i < table.getRowCount(); i++) {
                    //Loop through the columns in the current table
                    Senateur senateur= new Senateur();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        
                        senateur.setQualite(table.getText(i, j));
                        senateur.setNom(table.getText(i, j+1));
                        senateur.setPrenom(table.getText(i, j+2));
                        senateur.setMandat(table.getText(i, j+3));
                        senateur.setElu_Nomme(table.getText(i, j+4));
                        
                        senateurs.add(senateur);
                }
            }


        }
      }
      senateurRepository.saveAll(senateurs);
    
        }
}
