package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
//import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//import static org.apache.poi.xssf.eventusermodel.XSSFReader.XMLSheetRefReader.SHEET;

@Service
@RequiredArgsConstructor
public class ImportService {

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











//            Sheet sheet = workbook.getSheet(SHEET);
//            Iterator<Row> rows = sheet.iterator();
//
//            List<Localite> localites = new ArrayList<Localite>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                Localite localite = new Localite();
//
//                int cellIdx = 0;
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    switch (cellIdx) {
//                        case 0:
//                            localite.setCodeLocalite((int) currentCell.getNumericCellValue());
//                            break;
//
//                        case 1:
//                            localite.setLibelle(currentCell.getStringCellValue());
//                            break;
//
//                        case 2:
//                           localite.setPNombreMenage((int) currentCell.getNumericCellValue());
//                            break;
//
//                        case 3:
//                            localite.setPPolutaion((float) currentCell.getNumericCellValue());
//                            break;
//
//                        case 4:
//                            localite.setIEEcodeMaternelle(currentCell.getStringCellValue());
//                            break;
//                        case 5:
//                            localite.setIEEcodePrimaire(currentCell.getStringCellValue());
//                            break;
//                        case 6:
//                            localite.setIEEcodeSecondaire(currentCell.getStringCellValue());
//                            break;
//                        default:
//                            break;
//                    }
//
//                    cellIdx++;
//                }
//
//                localites.add(localite);
//            }
//
//            workbook.close();
//
//            return localites;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
















    //ArrayList localite= new ArrayList<Localite>();

    //InputStream inputStream ;
    //Localite localite= new Localite();


//            Workbook workbook= new XSSFWorkbook();
//            Sheet sheet = workbook.getSheet(SHEET);
//            Iterator<Row> rows = sheet.iterator();
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    localite.Libelle = currentCell.getStringCellValue();
//                    localite.PNombreMenage = (int) currentCell.getNumericCellValue();
//                    localite.PPolutaion = (float) currentCell.getNumericCellValue();
//                    localite.IEEcodeMaternelle= currentCell.getStringCellValue();
//                    localite.IEEcodePrimaire= currentCell.getStringCellValue();
//                    localite.IEEcodeSecondaire= currentCell.getStringCellValue();
//
//                    localiteRepository.save(localite);
//                }
//            }
//
//                workbook.close();
//
//
//
//
//
//            public List<Localite> importDataLocalite (MultipartFile) file) throws IOException {
//                List<Localite> localites = new ArrayList<>();
//
//
//                try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
//                    // Assumez que la première feuille de calcul contient les données
//                    Sheet sheet = workbook.getSheetAt(0);
//
//                    // Iterate through each row
//                    Iterator<Row> rowIterator = sheet.iterator();
//                    while (rowIterator.hasNext()) {
//                        Row row = rowIterator.next();
//
//                        // Skip the header row if it exists
//                        if (row.getRowNum() == 0) {
//                            continue;
//                        }
//
//
//
//                        // Iterate through each cell
//                        Iterator<Cell> cellIterator = row.cellIterator();
//                        while (cellIterator.hasNext()) {
//                            Cell cell = cellIterator.next();
//                            int columnIndex = cell.getColumnIndex();
//
//                            // Assurez-vous d'ajuster les indices de colonne en fonction de votre structure Excel
//                            switch (columnIndex) {
//                                case 0:
//                                    votreEntite.setColonne1(cell.getStringCellValue());
//                                    break;
//                                case 1:
//                                    votreEntite.setColonne2((int) cell.getNumericCellValue());
//                                    break;
//                                // Ajoutez d'autres cas pour les autres colonnes
//                                // ...
//                            }
//                        }
//
//                        vosEntites.add(votreEntite);
//                    }
//                }
//
//                return vosEntites;
//            }
}

