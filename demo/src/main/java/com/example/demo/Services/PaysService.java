package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.Pays;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

import lombok.Data;
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
@Data
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public List<Pays> getAllPays() {
        return paysRepository.findAll();
    }

    public Optional<Pays> getPaysByCode(int code) {
        return paysRepository.findById(code);
    }

    public Pays savePays(Pays pays) {
        return paysRepository.save(pays);
    }

    public void deletePays(int code) {
        paysRepository.deleteById(code);
    }

    public void saveExcelData(PaysRepository paysRepository, InputStream inputStream) throws IOException {
        Set<Pays> pays = new HashSet<Pays>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {

                if (row.getRowNum() > 0) {

                    Pays pays1= new Pays();
                    pays1.setNumEnregistrement((int) row.getCell(0).getNumericCellValue() );
                    pays1.setCodePays((int) row.getCell(1).getNumericCellValue());
                    pays1.setLibelle(row.getCell(2).getStringCellValue());
                    pays1.setMasculin((int) row.getCell(3).getNumericCellValue());
                    pays1.setFeminin((int) row.getCell(4).getNumericCellValue());
                    pays1.setTotal((int) row.getCell(5).getNumericCellValue());
                    pays1.setAccessible(row.getCell(6).getStringCellValue());
                    pays1.setDateCreation(row.getCell(7).getStringCellValue());
                    pays1.setDensite(row.getCell(8).getStringCellValue());
                    pays1.setSuperficie((int) row.getCell(9).getNumericCellValue());
                    pays1.setNbRegion((int) row.getCell(10).getNumericCellValue());
                    pays1.setNbDepartement((int) row.getCell(11).getNumericCellValue());
                    pays1.setNbCommune((int) row.getCell(12).getNumericCellValue());
                    pays1.setNbLocalite((int) row.getCell(13).getNumericCellValue());
                    pays1.setDateIndependance(row.getCell(14).getStringCellValue());
                    pays1.setDateReunification(row.getCell(15).getStringCellValue());
                    pays1.setDateUnification(row.getCell(16).getStringCellValue());

                    pays.add(pays1);
                }


            });

            paysRepository.saveAll(pays);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(PaysRepository paysRepository, InputStream inputStream) throws IOException {
     Set<Pays> pays = new HashSet<Pays>();
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
                    Pays pays1= new Pays();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                        String CodePays=table.getText(i, j);
                        pays1.setCodePays(Integer.parseInt(CodePays.trim()));
                        pays1.setLibelle(table.getText(i, j+1));
                        pays1.setAccessible(table.getText(i, j+2));
                        pays1.setDensite(table.getText(i, j+3));
                        String Superficie=table.getText(i,j+4);
                        pays1.setSuperficie(Integer.parseInt(Superficie.trim()));
                        pays1.setDateIndependance(table.getText(i, j+5));
                        pays1.setDateReunification(table.getText(i, j+6));
                        pays1.setDateUnification(table.getText(i, j+7));
                        
                        pays.add(pays1);

                }
            }


        }
      }
      paysRepository.saveAll(pays);
    
        }

}
