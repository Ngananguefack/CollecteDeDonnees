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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
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
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Pays pays1 = new Pays();
                Cell cell;

                cell = CellUtil.getCell(row, 0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setNumEnregistrement((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 1);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setCodePays((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 2);
                if (cell != null) {
                    pays1.setLibelle(cell.getStringCellValue());
                }

                cell = CellUtil.getCell(row, 3);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setMasculin((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 4);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setFeminin((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 5);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setTotal((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 6);
                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        pays1.setAccessible(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        pays1.setAccessible(String.valueOf((int) cell.getNumericCellValue()));
                    }
                }

                cell = CellUtil.getCell(row, 7);
                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        pays1.setDateCreation(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        pays1.setDateCreation(String.valueOf((int) cell.getNumericCellValue()));
                    }
                }

                cell = CellUtil.getCell(row, 8);
                if (cell != null) {
                    if (cell.getCellType() == CellType.STRING) {
                        pays1.setDensite(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        pays1.setDensite(String.valueOf((int) cell.getNumericCellValue()));
                    }
                }
                cell = CellUtil.getCell(row, 9);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setSuperficie((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 10);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setNbRegion((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 11);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setNbDepartement((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 12);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setNbCommune((int) cell.getNumericCellValue());
                }

                cell = CellUtil.getCell(row, 13);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    pays1.setNbLocalite((int) cell.getNumericCellValue());
                }
                cell = CellUtil.getCell(row, 14);
                if (cell != null) {
                    pays1.setDateIndependance(cell.getStringCellValue());
                }

                cell = CellUtil.getCell(row, 15);
                if (cell != null) {
                    pays1.setDateReunification(cell.getStringCellValue());
                }

                cell = CellUtil.getCell(row, 16);
                if (cell != null) {
                    pays1.setDateUnification(cell.getStringCellValue());
                }

                pays.add(pays1);
            }

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
