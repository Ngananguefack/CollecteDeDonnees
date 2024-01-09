package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
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
public class PaysagesUrbainsService {

    @Autowired
    private PaysagesUrbainsRepository paysagesUrbainsRepository;

    public List<PaysagesUrbains> getAllPaysagesUrbains() {
        return paysagesUrbainsRepository.findAll();
    }

    public Optional<PaysagesUrbains> getPaysagesUrbainsById(int id) {
        return paysagesUrbainsRepository.findById(id);
    }

    public PaysagesUrbains savePaysagesUrbains(PaysagesUrbains paysagesUrbains) {
        return paysagesUrbainsRepository.save(paysagesUrbains);
    }

    public void deletePaysagesUrbains(int id) {
        paysagesUrbainsRepository.deleteById(id);
    }

    public void saveExcelData(PaysagesUrbainsRepository paysagesUrbainsRepository, InputStream inputStream) throws IOException {
        Set<PaysagesUrbains> paysagesUrbains = new HashSet<PaysagesUrbains>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                PaysagesUrbains paysagesUrbains1= new PaysagesUrbains();
               paysagesUrbains1.setUnitePaysage(row.getCell(0).getStringCellValue());
               paysagesUrbains1.setUtilisation(row.getCell(1).getStringCellValue());
               paysagesUrbains1.setPotentialite(row.getCell(2).getStringCellValue());
               paysagesUrbains1.setUtilisateur(row.getCell(3).getStringCellValue());
               paysagesUrbains1.setProbleme(row.getCell(4).getStringCellValue());
               paysagesUrbains1.setCauses(row.getCell(5).getStringCellValue());
               paysagesUrbains1.setConsequences(row.getCell(6).getStringCellValue());
                paysagesUrbains1.setSolutions(row.getCell(7).getStringCellValue());

                paysagesUrbains.add(paysagesUrbains1);
            });

            paysagesUrbainsRepository.saveAll(paysagesUrbains);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(PaysagesUrbainsRepository paysagesUrbainsRepository, InputStream inputStream) throws IOException {
     Set<PaysagesUrbains> paysagesUrbains = new HashSet<PaysagesUrbains>();
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
                    PaysagesUrbains paysagesUrbains1=new PaysagesUrbains();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        
                        paysagesUrbains1.setUnitePaysage(table.getText(i, j));
                        paysagesUrbains1.setUtilisation(table.getText(i, j+1));
                        paysagesUrbains1.setPotentialite(table.getText(i, j+2));
                        paysagesUrbains1.setUtilisateur(table.getText(i, j+3));
                        paysagesUrbains1.setProbleme(table.getText(i, j+4));
                        paysagesUrbains1.setCauses(table.getText(i, j+5));
                        paysagesUrbains1.setConsequences(table.getText(i, j+6));
                        paysagesUrbains1.setSolutions(table.getText(i, j+7));

                        paysagesUrbains.add(paysagesUrbains1);

                        
                }
            }


        }
      }
      paysagesUrbainsRepository.saveAll(paysagesUrbains);
    
        }
}
