package com.example.demo.Services;

import com.example.demo.Entities.Cadre;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.CadreRepository;
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
public class CadreService {

    @Autowired
    private CadreRepository cadreRepository;

    public List<Cadre> getAllCadres() {
        return cadreRepository.findAll();
    }

    public Optional<Cadre> getCadreById(int id) {
        return cadreRepository.findById(id);
    }

    public Cadre saveCadre(Cadre cadre) {
        return cadreRepository.save(cadre);
    }

    public void deleteCadre(int id) {
        cadreRepository.deleteById(id);
    }

    public void saveExcelData(CadreRepository cadreRepository, InputStream inputStream) throws IOException {
        Set<Cadre> cadres = new HashSet<Cadre>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Cadre cadre= new Cadre();
                cadre.setIdCadre((int) row.getCell(0).getNumericCellValue());
                cadre.setCNiveau((int)row.getCell(1).getNumericCellValue());
                cadre.setCadre(row.getCell(2).getStringCellValue());
                cadre.setAccessible(row.getCell(3).getStringCellValue());

                cadres.add(cadre);
            });

            cadreRepository.saveAll(cadres);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(CadreRepository cadreRepository, InputStream inputStream) throws IOException {
     Set<Cadre> cadres = new HashSet<Cadre>();
        PdfDocument pdf= new PdfDocument(inputStream);
      //Create a PdfTableExtractor instance
      PdfTableExtractor extractor = new PdfTableExtractor(pdf);

      //Loop through the pages in the PDF
      for (int pageIndex = 1; pageIndex < pdf.getPages().getCount(); pageIndex++) {
        //Extract tables from the current page into a PdfTable array
        PdfTable[] tableLists = extractor.extractTable(pageIndex);
        
        //If any tables are found
        if (tableLists != null && tableLists.length > 0) {
            //Loop through the tables in the array
            for (PdfTable table : tableLists) {
                //Loop through the rows in the current table
                for (int i = 0; i < table.getRowCount(); i++) {
                    //Loop through the columns in the current table
                    Cadre cadre = new Cadre();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String IdCadre=table.getText(i, j);
                        cadre.setIdCadre(Integer.parseInt(IdCadre.trim()));
                        String CNiveau=table.getText(i,j+1);
                        cadre.setCNiveau(Integer.parseInt(CNiveau.trim()));
                        cadre.setCadre(table.getText(i, j+2));
                        cadre.setAccessible(table.getText(i,j+3));

                        cadres.add(cadre);
                }
            }


        }
      }
      cadreRepository.saveAll(cadres);
    
        }
}
