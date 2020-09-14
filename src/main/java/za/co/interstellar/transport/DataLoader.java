/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport;

import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.interstellar.transport.repository.TrafficRepo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import za.co.interstellar.transport.entity.Planet;
import za.co.interstellar.transport.entity.Route;
import za.co.interstellar.transport.entity.Traffic;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.POICellValuesUtil;

/**
 *
 * @author VukosiNyeleti
 */
@Component
public class DataLoader {

    @Autowired
    private TrafficRepo trafficRepo;

    @Autowired
    private RoutesRepo routesRepo;

    @Autowired
    private PlanetsRepo planetRepo;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        loadDataFromXlsFile();
    }

    @PreDestroy
    public void removeData() {
    }

    private void loadDataFromXlsFile() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("HR-Offsite-AssignmentV30.xlsx").getFile());

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);

            int sheetNumber = workbook.getNumberOfSheets();

            int rowId = 1;

            for (int i = 0; i < sheetNumber; i++) {

                Sheet datatypeSheet = workbook.getSheetAt(i);
                Iterator<Row> iterator = datatypeSheet.iterator();

                while (iterator.hasNext()) {

                    Row currentRow = iterator.next();

                    if (currentRow.getRowNum() > 0) {
                        if (i == 0) {
                            Planet planet = new Planet();
                            planet.setPlanetNode(POICellValuesUtil.getCellValueAsString(currentRow.getCell(0)));
                            planet.setPlanetName(POICellValuesUtil.getCellValueAsString(currentRow.getCell(1)));
                            planetRepo.save(planet);

                        } else if (i == 1) {
                            Route route = new Route();
                            route.setPlanetOrigin(POICellValuesUtil.getCellValueAsString(currentRow.getCell(1)));
                            route.setPlanetDestination(POICellValuesUtil.getCellValueAsString(currentRow.getCell(2)));
                            String val = currentRow.getCell(3).getNumericCellValue() + "";
                            route.setDistance(Double.valueOf(val));
                            routesRepo.save(route);

                        } else if (i == 2) {
                            Traffic traffic = new Traffic();
                            traffic.setPlanetOrigin(POICellValuesUtil.getCellValueAsString(currentRow.getCell(1)));
                            traffic.setPlanetDestination(POICellValuesUtil.getCellValueAsString(currentRow.getCell(2)));
                            String val = currentRow.getCell(3).getNumericCellValue() + "";

                            traffic.setTrafficDelay(new BigDecimal(val));
                            trafficRepo.save(traffic);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
