package com.example.demo.Services;

import com.example.demo.Entities.Chefferie;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.ChefferieRepository;
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
public class ChefferieService {

    @Autowired
    private ChefferieRepository chefferieRepository;

    public List<Chefferie> getAllChefferies() {
        return chefferieRepository.findAll();
    }

    public Optional<Chefferie> getChefferieById(int id) {
        return chefferieRepository.findById(id);
    }

    public Chefferie saveChefferie(Chefferie chefferie) {
        return chefferieRepository.save(chefferie);
    }

    public void deleteChefferie(int id) {
        chefferieRepository.deleteById(id);
    }

    public void saveExcelData(ChefferieRepository chefferieRepository, InputStream inputStream) throws IOException {
        Set<Chefferie> chefferies = new HashSet<Chefferie>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Chefferie chefferie= new Chefferie();
                chefferie.setLibelleChefferie(row.getCell(0).getStringCellValue());
                chefferie.setClassification(row.getCell(1).getStringCellValue());
                chefferie.setNActeDeterminant(row.getCell(2).getStringCellValue());
                chefferie.setNomDuChef(row.getCell(3).getStringCellValue());
                chefferie.setQualification(row.getCell(4).getStringCellValue());
                chefferie.setAnneAuTrone(row.getCell(5).getStringCellValue());

                chefferies.add(chefferie);
            });

            chefferieRepository.saveAll(chefferies);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(ChefferieRepository chefferieRepository, InputStream inputStream) throws IOException {
     Set<Chefferie> chefferies = new HashSet<Chefferie>();
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
                    Chefferie chefferie= new Chefferie();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        
                        chefferie.setLibelleChefferie(table.getText(i, j));
                        chefferie.setClassification(table.getText(i, j+1));
                        chefferie.setNActeDeterminant(table.getText(i, j+2));
                        chefferie.setNomDuChef(table.getText(i, j+3));
                        chefferie.setQualification(table.getText(i, j+4));
                        chefferie.setAnneAuTrone(table.getText(i, j+5));

                        chefferies.add(chefferie);
                }
            }


        }
      }
      chefferieRepository.saveAll(chefferies);
    
        }
}
