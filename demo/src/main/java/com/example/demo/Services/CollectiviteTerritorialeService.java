package com.example.demo.Services;

import com.example.demo.Entities.CollectiviteTerritoriale;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.CollectiviteTerritorialeRepository;
import com.example.demo.Repository.LocaliteRepository;
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
public class CollectiviteTerritorialeService {

    @Autowired
    private CollectiviteTerritorialeRepository collectiviteTerritorialeRepository;

    public List<CollectiviteTerritoriale> getAllCollectivitesTerritoriales() {
        return collectiviteTerritorialeRepository.findAll();
    }

    public Optional<CollectiviteTerritoriale> getCollectiviteTerritorialeById(int id) {
        return collectiviteTerritorialeRepository.findById(id);
    }

    public CollectiviteTerritoriale saveCollectiviteTerritoriale(CollectiviteTerritoriale collectiviteTerritoriale) {
        return collectiviteTerritorialeRepository.save(collectiviteTerritoriale);
    }

    public void deleteCollectiviteTerritoriale(int id) {
        collectiviteTerritorialeRepository.deleteById(id);
    }

    public void saveExcelData(CollectiviteTerritorialeRepository collectiviteTerritorialeRepository, InputStream inputStream) throws IOException {
        Set<CollectiviteTerritoriale> collectiviteTerritoriales = new HashSet<CollectiviteTerritoriale>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                CollectiviteTerritoriale collectiviteTerritoriale= new CollectiviteTerritoriale();
                collectiviteTerritoriale.setFiled(row.getCell(1).getStringCellValue());

                collectiviteTerritoriales.add(collectiviteTerritoriale);
            });

            collectiviteTerritorialeRepository.saveAll(collectiviteTerritoriales);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(CollectiviteTerritorialeRepository collectiviteTerritorialeRepository, InputStream inputStream) throws IOException {
     Set<CollectiviteTerritoriale> collectiviteTerritoriales = new HashSet<CollectiviteTerritoriale>();
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
                    CollectiviteTerritoriale collectiviteTerritoriale= new CollectiviteTerritoriale();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String codeLocalite=table.getText(i, j);
                        collectiviteTerritoriale.setFiled(table.getText(i, j));

                        collectiviteTerritoriales.add(collectiviteTerritoriale);
                }
            }


        }
      }
      collectiviteTerritorialeRepository.saveAll(collectiviteTerritoriales);
    
        }
}
