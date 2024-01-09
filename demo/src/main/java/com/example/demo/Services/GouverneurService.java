package com.example.demo.Services;

import com.example.demo.Entities.Gouverneur;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.GouverneurRepository;
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
public class GouverneurService {

    @Autowired
    private GouverneurRepository gouverneurRepository;

    public List<Gouverneur> getAllGouverneurs() {
        return gouverneurRepository.findAll();
    }

    public Optional<Gouverneur> getGouverneurById(int id) {
        return gouverneurRepository.findById(id);
    }

    public Gouverneur saveGouverneur(Gouverneur gouverneur) {
        return gouverneurRepository.save(gouverneur);
    }

    public void deleteGouverneur(int id) {
        gouverneurRepository.deleteById(id);
    }

    public void saveExcelData(GouverneurRepository gouverneurRepository, InputStream inputStream) throws IOException {
        Set<Gouverneur> gouverneurs = new HashSet<Gouverneur>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);

            workbook.getSheetAt(0).forEach(row -> {
                Gouverneur gouverneur= new Gouverneur();
               gouverneur.setNomGouverneur(row.getCell(0).getStringCellValue());
               gouverneur.setPrenomGouverneur(row.getCell(1).getStringCellValue());
               gouverneur.setElu_Nomme(row.getCell(2).getStringCellValue());

                gouverneurs.add(gouverneur);
            });

            gouverneurRepository.saveAll(gouverneurs);

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(GouverneurRepository gouverneurRepository, InputStream inputStream) throws IOException {
     Set<Gouverneur> gouverneurs = new HashSet<Gouverneur>();
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
                    Gouverneur gouverneur = new Gouverneur();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();

                        gouverneur.setNomGouverneur(table.getText(i, j));
                        gouverneur.setPrenomGouverneur(table.getText(i, j+1));
                        gouverneur.setElu_Nomme(table.getText(i, j+2));
                        
                        gouverneurs.add(gouverneur);
                }
            }


        }
      }
      gouverneurRepository.saveAll(gouverneurs);
    
        }
}
