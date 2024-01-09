package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Zone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ZoneRepository;
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
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getZoneById(int id) {
        return zoneRepository.findById(id);
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public void deleteZone(int id) {
        zoneRepository.deleteById(id);
    }

    public void saveExcelData(ZoneRepository zoneRepository, InputStream inputStream) throws IOException {
        Set<Zone> zones = new HashSet<Zone>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            //workbook.getSheet(0).for(int i=1; i<getRowCount; i++);;
            workbook.getSheetAt(0).forEach(row -> {
                Zone zone= new Zone();
                zone.setZone(row.getCell(0).getStringCellValue());
                zone.setZNiveau(row.getCell(1).getStringCellValue());
                zone.setNbDepartement((int) row.getCell(2).getNumericCellValue());
                zone.setNbCommune((int) row.getCell(3).getNumericCellValue());
                zone.setNbLocalite((int) row.getCell(4).getNumericCellValue());
                zone.setSuperficies((int) row.getCell(5).getNumericCellValue());

                zones.add(zone);
            });

            zoneRepository.saveAll(zones);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(ZoneRepository zoneRepository, InputStream inputStream) throws IOException {
     Set<Zone> zones = new HashSet<Zone>();
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
                    Zone zone= new Zone();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
               
                        zone.setZone(table.getText(i, j));
                        zone.setZNiveau(table.getText(i, j+1));
                        String Departement= table.getText(i, j+2);
                        zone.setNbDepartement(Integer.parseInt(Departement.trim()));
                        String NbCommune=table.getText(i, j+3);
                        zone.setNbCommune(Integer.parseInt(NbCommune.trim()));
                        String NbLocalite= table.getText(i, j+4);
                        zone.setNbLocalite(Integer.parseInt(NbLocalite.trim()));
                        String Superficies= table.getText(i, j+5);
                        zone.setSuperficies(Integer.parseInt(Superficies.trim()));
                        
                        zones.add(zone);
                }
            }


        }
      }
      zoneRepository.saveAll(zones);
    
        }

}
