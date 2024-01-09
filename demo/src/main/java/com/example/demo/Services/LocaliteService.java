package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocaliteService {

    @Autowired
    private LocaliteRepository localiteRepository;

    public List<Localite> getAllLocalites() {
        return localiteRepository.findAll();
    }

    public Optional<Localite> getLocaliteByCode(int codeLocalite) {
        return localiteRepository.findById(codeLocalite);
    }

    public Localite saveLocalite(Localite localite) {
        return localiteRepository.save(localite);
    }

    public void deleteLocalite(int codeLocalite) {
        localiteRepository.deleteById(codeLocalite);
    }

    private static final String EXCEL_FILE_PATH = "G:/test_localite.xlsx";

    private InputStream getFilePath() throws IOException {
        return new ClassPathResource(EXCEL_FILE_PATH).getInputStream();
    }

    public void saveExcelData(LocaliteRepository localiteRepository, InputStream inputStream) throws IOException {
        Set<Localite> localites = new HashSet<Localite>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Localite localite= new Localite();
                localite.setCodeLocalite((int) row.getCell(0).getNumericCellValue());
                localite.setLibelle(row.getCell(1).getStringCellValue());
                localite.setPNombreMenage((int) row.getCell(2).getNumericCellValue());
                localite.setPPolutaion((float) row.getCell(3).getNumericCellValue());
                localite.setIEEcodeMaternelle(row.getCell(4).getStringCellValue());
                localite.setIEEcodePrimaire(row.getCell(5).getStringCellValue());
                localite.setIEEcodeSecondaire(row.getCell(6).getStringCellValue());

                localites.add(localite);
            });

            localiteRepository.saveAll(localites);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(LocaliteRepository localiteRepository, InputStream inputStream) throws IOException {
     Set<Localite> localites = new HashSet<Localite>();
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
                    Localite localite = new Localite();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String codeLocalite=table.getText(i, j);
                        localite.setCodeLocalite(Integer.parseInt(codeLocalite.trim()));
                        localite.setLibelle(table.getText(i, j+1));
                        String PNombreMenage=table.getText(i, j+2);
                        localite.setPNombreMenage(Integer.parseInt(PNombreMenage.trim()));
                        String PPopulation=table.getText(i, j+3);
                        localite.setPPolutaion(Integer.parseInt(PPopulation.trim()));
                        localite.setIEEcodeMaternelle(table.getText(i, j+4));
                        localite.setIEEcodePrimaire(table.getText(i, j+5));
                        localite.setIEEcodeSecondaire(table.getText(i, j+6));
                        
                        localites.add(localite);
                }
            }


        }
      }
      localiteRepository.saveAll(localites);
    
        }
}
