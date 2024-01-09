package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Region;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.RegionRepository;
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
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionByCode(int code) {
        return regionRepository.findById(code);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public void deleteRegion(int code) {
        regionRepository.deleteById(code);
    }

    public void saveExcelData(RegionRepository regionRepository, InputStream inputStream) throws IOException {
        Set<Region> regions = new HashSet<Region>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Region region= new Region();
                region.setCodeRegion((int) row.getCell(0).getNumericCellValue());
                region.setLibelle(row.getCell(1).getStringCellValue());
                region.setAccessible(row.getCell(2).getStringCellValue());
                region.setDateCreation(row.getCell(3).getStringCellValue());
                region.setDensite((int) row.getCell(4).getNumericCellValue());
                region.setSuperficie((int) row.getCell(5).getNumericCellValue());

                regions.add(region);
            });

            regionRepository.saveAll(regions);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(RegionRepository regionRepository, InputStream inputStream) throws IOException {
     Set<Region> regions = new HashSet<Region>();
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
                    Region region= new Region();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String CodeRegion=table.getText(i, j);
                        region.setCodeRegion(Integer.parseInt(CodeRegion.trim()));
                        region.setLibelle(table.getText(i, j+1));
                        region.setAccessible(table.getText(i, j+2));
                        String Densite=table.getText(i, j+3);
                        region.setDateCreation(table.getText(i, j+4));
                        region.setDensite(Integer.parseInt(Densite.trim()));
                        String Superficie= table.getText(i,j+5);
                        region.setSuperficie(Integer.parseInt(Superficie.trim()));
                        
                        regions.add(region);
                }
            }


        }
      }
      regionRepository.saveAll(regions);
    
        }
}
