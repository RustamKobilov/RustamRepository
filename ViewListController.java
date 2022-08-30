package ExcelWork;

import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.awt.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ViewListController {


    public void ExcelLoad(int killometrFinish, int killometrStart, int dayKillometr, double startOil, double finishOil,
                          double dayOil, String carList, String driverList, double zapravkalitr, String nameAddOil,
                          LocalDate dataSegodnya, String carGaragNumber, String carModel) throws IOException {


        LocalDate dateOil = dataSegodnya;

        System.out.println("goy Print");
        File ExcelListShab = new File("D:/Users/RMTeX/IdeaProjects/List K.R.U/src/ExcelWork/pyt.xls");
        FileInputStream inputStream = new FileInputStream(ExcelListShab);

        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFCell cellname = sheet.getRow(16).getCell(0);
//        имя водителя
        cellname.setCellValue(driverList);

//        cell = sheet.getRow(16).getCell(34); место подписи водителя
//

        HSSFCell cellday = sheet.getRow(7).getCell(12);
//
//        день
        cellday.setCellValue(dataSegodnya.getDayOfMonth());


        HSSFCell cellmonth = sheet.getRow(7).getCell(19);
        //        месяц
        cellmonth.setCellValue(dataSegodnya.getMonthValue());


        HSSFCell cellyear = sheet.getRow(7).getCell(38);
//        год

        String dateinblank = dataSegodnya.toString();
        StringBuffer sd = new StringBuffer(dateinblank);
        sd.delete(4, 10);
        sd.delete(0, 2);
        String dateListsmall = sd.toString();

        cellyear.setCellValue(dateListsmall);


        HSSFCell cellcarname = sheet.getRow(10).getCell(0);
//        название машины
        cellcarname.setCellValue(carModel);

        HSSFCell cellcarnumber = sheet.getRow(10).getCell(18);
//        номер машины
        cellcarnumber.setCellValue(carList);

        HSSFCell cellcarnumbergarag = sheet.getRow(10).getCell(39);
//        номер машины в гараже
        cellcarnumbergarag.setCellValue(carGaragNumber);

        HSSFCell cellstartprobeg = sheet.getRow(8).getCell(68);
//        пробег при выезде
        cellstartprobeg.setCellValue(killometrStart);

        HSSFCell cellfinishprobeg = sheet.getRow(9).getCell(68);
//        пробег при возвращении
        cellfinishprobeg.setCellValue(killometrFinish);

        HSSFCell celldate1 = sheet.getRow(8).getCell(82);
        HSSFCell celldate2 = sheet.getRow(8).getCell(98);
        HSSFCell celldate11 = sheet.getRow(9).getCell(82);
        HSSFCell celldate22 = sheet.getRow(9).getCell(98);
//       даты в путевке


        DateTimeFormatter formatdatepetevoi = DateTimeFormatter.ofPattern("dd. MM. yyyy.");


        celldate1.setCellValue(formatdatepetevoi.format(dataSegodnya));
        celldate2.setCellValue(formatdatepetevoi.format(dataSegodnya));
        celldate11.setCellValue(formatdatepetevoi.format(dataSegodnya));
        celldate22.setCellValue(formatdatepetevoi.format(dataSegodnya));


        HSSFCell celldateoiladd = sheet.getRow(13).getCell(54);
        celldateoiladd.setCellValue(formatdatepetevoi.format(dateOil));
//      даты заправки

        HSSFCell celltypeoil = sheet.getRow(13).getCell(76);
        celltypeoil.setCellValue(nameAddOil);
//      тип топлива

        HSSFCell cellmanyoil = sheet.getRow(13).getCell(95);
        cellmanyoil.setCellValue(zapravkalitr);
//      топливо заправлено

        if (zapravkalitr <= 0.1) {
            System.out.println("voshli");
//            dateOil = null;
//            nameAddOil = null;

            celldateoiladd.setCellValue("");
//      даты заправки

            celltypeoil.setCellValue("");
//      тип топлива

            cellmanyoil.setCellValue("");
//      топливо заправлено

            System.out.println("vushli");
        }




        HSSFCell cellstartoil = sheet.getRow(13).getCell(114);
        cellstartoil.setCellValue(startOil);
//      топливо при выезде

        HSSFCell cellfinishoil = sheet.getRow(13).getCell(133);
        cellfinishoil.setCellValue(finishOil);
//      топливо при возвращении

        Classes.startComboboxUpdate streetAdd = new Classes.startComboboxUpdate();
        ObservableList<Classes.StreetMogilev> Street = streetAdd.StreetLook();

        for (int x = 21; x < 29; x++) {
            HSSFCell cellstreetname = sheet.getRow(x).getCell(76);
            String NameStreet1 = Street.get((int)(Math.random()*(Street.size()))).getStreet();
            cellstreetname.setCellValue(NameStreet1);
        }
        //      улицы первая сторона


        for (int x = 44; x < 51; x++) {
            HSSFCell cellstreet2 = sheet.getRow(x).getCell(104);
            String NameStreet = Street.get((int)(Math.random()*(Street.size()))).getStreet();
            cellstreet2.setCellValue(NameStreet);
        }
        //      улицы вторая сторона

        HSSFCell celldayoil1 = sheet.getRow(57).getCell(0);
        HSSFCell celldayoil2 = sheet.getRow(57).getCell(10);
        celldayoil1.setCellValue(dayOil);
        celldayoil2.setCellValue(dayOil);
        //      пробег за день

        HSSFCell celldaykm1 = sheet.getRow(57).getCell(70);
        HSSFCell celldaykm2 = sheet.getRow(57).getCell(90);
        celldaykm1.setCellValue(dayKillometr);
        celldaykm2.setCellValue(dayKillometr);
        //      киллометраж за день

        inputStream.close();

        FileOutputStream out = new FileOutputStream(ExcelListShab);
        workbook.write(out);
        out.close();

        System.out.println("my v kontse excel");


    }

    public void PrintPytevoiList() {


        System.out.println("poshla pechat na printer");
        try {
            Desktop.getDesktop().print(new File("pyt.xls"));

        }
        catch (Exception et){
            et.printStackTrace();
        }
        System.out.println("pechat na printer konets");

    }
}





