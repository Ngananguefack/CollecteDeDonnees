package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Probleme;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ProblemeRepository;
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
public class ProblemeService {

    @Autowired
    private ProblemeRepository problemeRepository;

    public List<Probleme> getAllProblemes() {
        return problemeRepository.findAll();
    }

    public Optional<Probleme> getProblemeById(int id) {
        return problemeRepository.findById(id);
    }

    public Probleme saveProbleme(Probleme probleme) {
        return problemeRepository.save(probleme);
    }

    public void deleteProbleme(int id) {
        problemeRepository.deleteById(id);
    }

    public void saveExcelData(ProblemeRepository problemeRepository, InputStream inputStream) throws IOException {
        Set<Probleme> problemes = new HashSet<Probleme>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Probleme probleme= new Probleme();
                probleme.setProbleme(row.getCell(0).getStringCellValue());
                probleme.setArchive(row.getCell(1).getStringCellValue());
                problemes.add(probleme);
            });

            problemeRepository.saveAll(problemes);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(ProblemeRepository problemeRepository, InputStream inputStream) throws IOException {
     Set<Probleme> problemes = new HashSet<Probleme>();
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
                    Probleme probleme= new Probleme();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String codeLocalite=table.getText(i, j);

                        probleme.setProbleme(table.getText(i, j));
                        probleme.setArchive(table.getText(i, j+1));
                
                        
                        problemes.add(probleme);
                }
            }


        }
      }
      problemeRepository.saveAll(problemes);
    
        }
}
