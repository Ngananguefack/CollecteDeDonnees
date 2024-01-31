package com.example.demo.Services;

import com.example.demo.Entities.Localite;
import com.example.demo.Entities.PaysagesUrbains;
import com.example.demo.Entities.Zone;
import com.example.demo.Repository.LocaliteRepository;
import com.example.demo.Repository.PaysagesUrbainsRepository;
import com.example.demo.Repository.ZoneRepository;
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
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getZoneById(int id) {
        return zoneRepository.findById(id);
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    public void deleteZone(int id) {
        zoneRepository.deleteById(id);
    }

    public void saveExcelData(ZoneRepository zoneRepository, InputStream inputStream) throws IOException {
        Set<Zone> zones = new HashSet<>();
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Zone zone = new Zone();

                for (int i = 0; i <= 33; i++) {
                    Cell cell = CellUtil.getCell(row, i);
                    if (cell != null) {
                        switch (i) {
                            case 0:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setNumEnregistrement((int) cell.getNumericCellValue());
                                }
                                break;
                            case 1:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setIdZone((int) cell.getNumericCellValue());
                                }
                                break;
                            // Add cases for other attributes as needed
                            case 2:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setCodePays((int) cell.getNumericCellValue());
                                }
                                break;
                            case 3:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setPays(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setPays(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            // Repeat the pattern for other attributes
                            case 7:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setDepartement(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setDepartement(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;

                            case 8:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setCodeCommune((int) cell.getNumericCellValue());
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setCommune(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setCommune(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;

                            case 10:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setCodeLocalite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 11:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setLocalite(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setLocalite(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;

                            case 12:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setZone(cell.getStringCellValue());
                                }
                                break;
                            case 13:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setZNiveau(cell.getStringCellValue());
                                }
                                break;
                            case 14:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setNbRegion((int) cell.getNumericCellValue());
                                }
                                break;
                            case 15:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setNbDepartement((int) cell.getNumericCellValue());
                                }
                                break;
                            case 16:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setNbCommune((int) cell.getNumericCellValue());
                                }
                                break;
                            case 17:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setNbLocalite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 18:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setSuperficies((int) cell.getNumericCellValue());
                                }
                                break;
                            case 19:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setDensite((int) cell.getNumericCellValue());
                                }
                                break;
                            case 20:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setAdresse(cell.getStringCellValue());
                                }
                                break;
                            case 21:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setDate(cell.getStringCellValue());
                                }
                                break;
                            case 22:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setEmail(cell.getStringCellValue());
                                }
                                break;
                            case 23:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setTelephone((int) cell.getNumericCellValue());
                                }
                                break;
                            case 24:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setIcone(cell.getStringCellValue());
                                }
                                break;
                            case 25:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setImage(cell.getStringCellValue());
                                }
                                break;
                            case 26:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setMasculin((int) cell.getNumericCellValue());
                                }
                                break;
                            case 27:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setFeminin((int) cell.getNumericCellValue());
                                }
                                break;
                            case 28:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setTotal((int) cell.getNumericCellValue());
                                }
                                break;
                            case 29:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setAccessible(cell.getStringCellValue());
                                } else if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setAccessible(String.valueOf((int) cell.getNumericCellValue()));
                                }
                                break;
                            case 30:
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    zone.setLocalisation(cell.getStringCellValue());
                                }
                                break;
                            case 31:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setDelimitationJSON(cell.getStringCellValue());
                                }
                                break;
                            case 32:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setLimites(cell.getStringCellValue());
                                }
                                break;
                            case 33:
                                if (cell.getCellType() == CellType.STRING) {
                                    zone.setLocalisation(cell.getStringCellValue());
                                }
                                break;
                             default:
                                // Handle unknown columns or ignore them
                                break;

                        }
                    }
                }

                zones.add(zone);
            }

            zoneRepository.saveAll(zones);

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

        private InputStream getFilePaths() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test_localite.pdf");
    return inputStream;
}

        public void savePdfData(ZoneRepository zoneRepository, InputStream inputStream) throws IOException {
     Set<Zone> zones = new HashSet<Zone>();
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
                    Zone zone= new Zone();
                    //localite.setCodeLocalite();
                    int j = 0;
                        //Extract data from the current table cell and append to the StringBuilder 
                        //StringBuilder stringBuilder = new StringBuilder();
               
                        zone.setZone(table.getText(i, j));
                        zone.setZNiveau(table.getText(i, j+1));
                        String Departement= table.getText(i, j+2);
                        zone.setNbDepartement(Integer.parseInt(Departement.trim()));
                        String NbCommune=table.getText(i, j+3);
                        zone.setNbCommune(Integer.parseInt(NbCommune.trim()));
                        String NbLocalite= table.getText(i, j+4);
                        zone.setNbLocalite(Integer.parseInt(NbLocalite.trim()));
                        String Superficies= table.getText(i, j+5);
                        zone.setSuperficies(Integer.parseInt(Superficies.trim()));
                        
                        zones.add(zone);
                }
            }


        }
      }
      zoneRepository.saveAll(zones);
    
        }

}
