package com.example.demo.Services;

import com.example.demo.Repository.LocaliteRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentInformation;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
@Service
public class LocalisationServicePdf {

        //Load a sample PDF document


    //private static final String pdf = "G:/test_localite.pdf";

        private InputStream getFilePath() throws IOException {
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
