package com.example.demo.Services;

import com.example.demo.Entities.Infrastructure;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.InfrastructureRepository;
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
public class InfrastructureService {

    @Autowired
    private InfrastructureRepository infrastructureRepository;

    public List<Infrastructure> getAllInfrastructures() {
        return infrastructureRepository.findAll();
    }

    public Optional<Infrastructure> getInfrastructureById(int id) {
        return infrastructureRepository.findById(id);
    }

    public Infrastructure saveInfrastructure(Infrastructure infrastructure) {
        return infrastructureRepository.save(infrastructure);
    }

    public void deleteInfrastructure(int id) {
        infrastructureRepository.deleteById(id);
    }

    public void saveExcelData(InfrastructureRepository infrastructureRepository, InputStream inputStream) throws IOException {
        Set<Infrastructure> infrastructures = new HashSet<Infrastructure>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Infrastructure infrastructure= new Infrastructure();
                infrastructure.setLibelle(row.getCell(1).getStringCellValue());
                infrastructure.setEtat(row.getCell(2).getStringCellValue());
                infrastructure.setQte((int) row.getCell(3).getNumericCellValue());
                infrastructure.setBeneficiaire(row.getCell(4).getStringCellValue());
                infrastructure.setObservation(row.getCell(5).getStringCellValue());
                infrastructure.setGroupeInfrastructure(row.getCell(6).getStringCellValue());

                infrastructures.add(infrastructure);
            });

            infrastructureRepository.saveAll(infrastructures);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

            private InputStream getFilePath() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(InfrastructureRepository infrastructureRepository, InputStream inputStream) throws IOException {
     Set<Infrastructure> infrastructures = new HashSet<Infrastructure>();
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
                    Infrastructure infrastructure= new Infrastructure();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
                       
                       infrastructure.setLibelle(table.getText(i, j));
                       infrastructure.setEtat(table.getText(i, j+1));
                        String Qte=table.getText(i, j+2);
                        infrastructure.setQte(Integer.parseInt(Qte.trim()));
                        infrastructure.setBeneficiaire(table.getText(i, j+3));
                        infrastructure.setObservation(table.getText(i, j+4));
                        infrastructure.setGroupeInfrastructure(table.getText(i, j+5));
                        
                        infrastructures.add(infrastructure);
                }
            }


        }
      }
      infrastructureRepository.saveAll(infrastructures);
    
        }
}
