package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PossibiliteDeZone;
import com.example.demo.Entities.PotentialitesDesZones;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PotentialitesDesZonesRepository;
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
public class PotentialitesDesZonesService {

    @Autowired
    private PotentialitesDesZonesRepository potentialitesDesZonesRepository;

    public List<PotentialitesDesZones> getAllPotentialitesDesZones() {
        return potentialitesDesZonesRepository.findAll();
    }

    public Optional<PotentialitesDesZones> getPotentialitesDesZonesById(int id) {
        return potentialitesDesZonesRepository.findById(id);
    }

    public PotentialitesDesZones savePotentialitesDesZones(PotentialitesDesZones potentialitesDesZones) {
        return potentialitesDesZonesRepository.save(potentialitesDesZones);
    }

    public void deletePotentialitesDesZones(int id) {
        potentialitesDesZonesRepository.deleteById(id);
    }

    public void saveExcelData(PotentialitesDesZonesRepository potentialitesDesZonesRepository, InputStream inputStream) throws IOException {
        Set<PotentialitesDesZones> possibiliteDeZones = new HashSet<PotentialitesDesZones>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                PotentialitesDesZones potentialitesDesZones1= new PotentialitesDesZones();
                potentialitesDesZones1.setPotentialite(row.getCell(0).getStringCellValue());
                potentialitesDesZones1.setRessource(row.getCell(1).getStringCellValue());

                possibiliteDeZones.add(potentialitesDesZones1);
            });

            potentialitesDesZonesRepository.saveAll(possibiliteDeZones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(PotentialitesDesZonesRepository potentialitesDesZonesRepository, InputStream inputStream) throws IOException {
     Set<PotentialitesDesZones> potentialitesDesZones = new HashSet<PotentialitesDesZones>();
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
                    PotentialitesDesZones potentialitesDesZones1= new PotentialitesDesZones();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                       
                        potentialitesDesZones1.setPotentialite(table.getText(i, j));
                        potentialitesDesZones1.setRessource(table.getText(i, j+1));
                        
                        potentialitesDesZones.add(potentialitesDesZones1);

                }
            }


        }
      }
      potentialitesDesZonesRepository.saveAll(potentialitesDesZones);
    
        }

}
