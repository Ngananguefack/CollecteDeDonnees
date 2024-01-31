package com.example.demo.Services;

import com.example.demo.Entities.Cadre;
import com.example.demo.Entities.Localite;
import com.example.demo.Repository.CadreRepository;
import com.example.demo.Repository.LocaliteRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

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
        Set<Cadre> cadres = new HashSet<>();
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Cadre cadre = new Cadre();

                for (int i = 0; i <= 9; i++) {
                    Cell cell = CellUtil.getCell(row, i);
                    if (cell != null) {
                        switch (i) {
                            case 0:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setNumEnregistrement((int) cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdCadre((int) cell.getNumericCellValue());
                                }
                                break;
                            case 2:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdSecteur((int) cell.getNumericCellValue());
                                }
                                break;
                            case 3:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdChapitre((int) cell.getNumericCellValue());
                                }
                                break;
                            case 4:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdProgramme((int) cell.getNumericCellValue());
                                }
                                break;
                            case 5:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdAction((int) cell.getNumericCellValue());
                                }
                                break;
                            case 6:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setIdActivite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 7:
                                if (cell.getCellType() == CellType.STRING) {
                                    cadre.setCadre(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setCadre(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            case 8:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setCNiveau((int) cell.getNumericCellValue());
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == CellType.STRING) {
                                    cadre.setAccessible(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    cadre.setAccessible(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            default:
                                // Handle unknown columns or ignore them
                                break;
                        }
                    }
                }

                cadres.add(cadre);
            }

            cadreRepository.saveAll(cadres);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
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
