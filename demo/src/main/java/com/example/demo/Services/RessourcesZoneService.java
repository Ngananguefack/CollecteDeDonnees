package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.RessourcesZone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RessourcesZoneRepository;
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
public class RessourcesZoneService {

    @Autowired
    private RessourcesZoneRepository ressourcesZoneRepository;

    public List<RessourcesZone> getAllRessourcesZones() {
        return ressourcesZoneRepository.findAll();
    }

    public Optional<RessourcesZone> getRessourcesZoneById(int id) {
        return ressourcesZoneRepository.findById(id);
    }

    public RessourcesZone saveRessourcesZone(RessourcesZone ressourcesZone) {
        return ressourcesZoneRepository.save(ressourcesZone);
    }

    public void deleteRessourcesZone(int id) {
        ressourcesZoneRepository.deleteById(id);
    }

    public void saveExcelData(RessourcesZoneRepository ressourcesZoneRepository, InputStream inputStream) throws IOException {
        Set<RessourcesZone> ressourcesZones = new HashSet<RessourcesZone>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                RessourcesZone ressourcesZone= new RessourcesZone();
                ressourcesZone.setRessource(row.getCell(0).getStringCellValue());
                ressourcesZone.setCarateristique(row.getCell(1).getStringCellValue());
                ressourcesZone.setUtilisationActuelle(row.getCell(2).getStringCellValue());
                ressourcesZone.setAccesControler(row.getCell(3).getStringCellValue());
                ressourcesZone.setArchive(row.getCell(4).getStringCellValue());

                ressourcesZones.add(ressourcesZone);
            });

            ressourcesZoneRepository.saveAll(ressourcesZones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(RessourcesZoneRepository ressourcesZoneRepository, InputStream inputStream) throws IOException {
     Set<RessourcesZone> ressourcesZones = new HashSet<RessourcesZone>();
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
                    RessourcesZone ressourcesZone= new RessourcesZone();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();

                        ressourcesZone.setRessource(table.getText(i, j));
                        ressourcesZone.setCarateristique(table.getText(i, j+1));
                        ressourcesZone.setUtilisationActuelle(table.getText(i, j+2));
                        ressourcesZone.setAccesControler(table.getText(i, j+3));
                        ressourcesZone.setArchive(table.getText(i, j+4));
                        
                        ressourcesZones.add(ressourcesZone);

                }
            }


        }
      }
      ressourcesZoneRepository.saveAll(ressourcesZones);
    
        }
}
