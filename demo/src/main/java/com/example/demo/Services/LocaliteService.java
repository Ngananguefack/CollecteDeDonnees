package com.example.demo.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.Entities.Localite;
import com.example.demo.Repository.LocaliteRepository;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LocaliteService {

    private static final Logger log = LoggerFactory.getLogger(LocaliteService.class);

    @Autowired
    private LocaliteRepository localiteRepository;

    public List<Localite> getAllLocalites() {
        return localiteRepository.findAll();
    }

    public Optional<Localite> getLocaliteByCode(int codeLocalite) {
        return localiteRepository.findById(codeLocalite);
    }

    public Localite saveLocalite(Localite localite) {
        return localiteRepository.save(localite);
    }

    public void deleteLocalite(int codeLocalite) {
        localiteRepository.deleteById(codeLocalite);
    }

    private static final String EXCEL_FILE_PATH = "G:/test_localite.xlsx";

    private InputStream getFilePath() throws IOException {
        return new ClassPathResource(EXCEL_FILE_PATH).getInputStream();
    }

    public void saveExcelData(LocaliteRepository localiteRepository, InputStream inputStream) throws IOException {
        Set<Localite> localites = new HashSet<>();
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Localite localite = new Localite();


                log.info("Processing row:"+ row.getRowNum());

                for (int i = 0; i <= 49; i++) {  // Adjust the loop limit based on the number of columns in your Excel sheet
                    Cell cell = CellUtil.getCell(row, i);
                    log.info("Processing cell: " + i);
                    if (cell != null) {
                        switch (i) {
                            case 0:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setNumEnregistrement((int) cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setCodesLocalite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 2:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setCodeCommune((int) cell.getNumericCellValue());
                                }
                                break;
                            case 3:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setCodeLocalite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 4:
                                if (cell.getCellType() == CellType.STRING) {
                                    localite.setLibelle(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setLibelle(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            case 5:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setMasculin((int) cell.getNumericCellValue());
                                }
                                break;
                            case 6:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setFeminin((int) cell.getNumericCellValue());
                                }
                                break;
                            case 7:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setTotal((int) cell.getNumericCellValue());
                                }
                                break;
                            case 8:
                                if (cell.getCellType() == CellType.STRING) {
                                    localite.setChefferie(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setChefferie(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPNombreMenage((int) cell.getNumericCellValue());
                                }
                                break;
                            case 10:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPHomme((int) cell.getNumericCellValue());
                                }
                                break;
                            case 11:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPFemme((int) cell.getNumericCellValue());
                                }
                                break;
                            case 12:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPNombrePersHandicape((int) cell.getNumericCellValue());
                                }
                                break;
                            case 13:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPEnfants_5ans((int) cell.getNumericCellValue());
                                }
                                break;
                            case 14:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPEnfants_15ans((int) cell.getNumericCellValue());
                                }
                                break;
                            case 15:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setPPolutaion((float) cell.getNumericCellValue());
                                }
                                break;
                            case 16:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIEEcoleMaternelle((int) cell.getNumericCellValue());
                                }
                                break;
                            case 17:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIEEcolePrimaire((int) cell.getNumericCellValue());
                                }
                                break;
                            case 18:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIEEcoleSecondaire((int) cell.getNumericCellValue());
                                }
                                break;
                            case 19:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIEEColeTechnique((int) cell.getNumericCellValue());
                                }
                                break;
                            case 20:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIHForage((int) cell.getNumericCellValue());
                                }
                                break;
                            case 21:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIHPuits((int) cell.getNumericCellValue());
                                }
                                break;
                            case 22:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIHAdductionEau((int) cell.getNumericCellValue());
                                }
                                break;
                            case 23:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIHAutres((int) cell.getNumericCellValue());
                                }
                                break;
                            case 24:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setIHAutresDetails((int) cell.getNumericCellValue());
                                }
                                break;
                            case 25:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSCSI((int) cell.getNumericCellValue());
                                }
                                break;
                            case 26:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSCMA((int) cell.getNumericCellValue());
                                }
                                break;
                            case 27:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSHopital((int) cell.getNumericCellValue());
                                }
                                break;
                            case 28:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSPrivee((int) cell.getNumericCellValue());
                                }
                                break;
                            case 29:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSAutres((int) cell.getNumericCellValue());
                                }
                                break;
                            case 30:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setICSAutresDetails((int) cell.getNumericCellValue());
                                }
                                break;
                            case 31:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPFoyers((int) cell.getNumericCellValue());
                                }
                                break;
                            case 32:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPCentreFemme((int) cell.getNumericCellValue());
                                }
                                break;
                            case 33:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPCentreMultifonctionnel((int) cell.getNumericCellValue());
                                }
                                break;
                            case 34:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPCentreSociaux((int) cell.getNumericCellValue());
                                }
                                break;
                            case 35:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPAutres((int) cell.getNumericCellValue());
                                }
                                break;
                            case 36:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setISEPAutresDetails((int) cell.getNumericCellValue());
                                }
                                break;
                            case 37:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setEPMMagasins((int) cell.getNumericCellValue());
                                }
                                break;
                            case 38:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setEPMMarches((int) cell.getNumericCellValue());
                                }
                                break;
                            case 39:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setEPMAbbatoir((int) cell.getNumericCellValue());
                                }
                                break;
                            case 40:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setEPMGareRoutiere((int) cell.getNumericCellValue());
                                }
                                break;
                            case 41:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setEPMParcaBetail((int) cell.getNumericCellValue());
                                }
                                break;
                            case 42:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setACElectrification((int) cell.getNumericCellValue());
                                }
                                break;
                            case 43:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setACTelephone((int) cell.getNumericCellValue());
                                }
                                break;
                            case 44:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setAVRouteBitumee((int) cell.getNumericCellValue());
                                }
                                break;
                            case 45:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setAVRouteEnTerreAmenage((int) cell.getNumericCellValue());
                                }
                                break;
                            case 46:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setAVPiste((int) cell.getNumericCellValue());
                                }
                                break;
                            case 47:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setERAccessibleTouteSaison((int) cell.getNumericCellValue());
                                }
                                break;
                            // ... continue similarly for other attributes
                            case 48:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    localite.setOExistenceComiteDeveloppement((int) cell.getNumericCellValue());
                                }
                                break;
                            case 49:
                                if (cell.getCellType() == CellType.STRING) {
                                    localite.setAccessible(cell.getStringCellValue());
                                } else if (cell.getCellType()== CellType.NUMERIC) {
                                    localite.setAccessible(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            default:
                                // Handle unknown columns or ignore them
                                break;
                        }
                    }
                }

                log.info("Localite created: " + localite);

                localites.add(localite);
            }

            localiteRepository.saveAll(localites);
            log.info("Data saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error importing data: " + e.getMessage());
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
                        String IEEcoleMaternelle= table.getText(i,j+4);
                        localite.setIEEcoleMaternelle(Integer.parseInt(IEEcoleMaternelle.trim()));
                        String IEEcolePrimaire= table.getText(i,j+5);
                        localite.setIEEcolePrimaire(Integer.parseInt(IEEcolePrimaire.trim()));
                        String IEEcoleSecondaire= table.getText(i,j+6);
                        localite.setIEEcoleSecondaire(Integer.parseInt(IEEcoleSecondaire.trim()));
                        
                        localites.add(localite);
                }
            }


        }
      }
      localiteRepository.saveAll(localites);
    
        }
}
