package Classes;

import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class kalkulator {
    Double DayOilRunsOut ;
    Double FinishOil ;
    int DayKM ;

    public void kalculator1(int StartKM, int FinishKM, Double StartOil, Double addOil, Double NormMatch){
        int dayKM = FinishKM - StartKM;
        while (dayKM<=0){
            Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
            exeptionTypeComma.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma.setContentText("Внимание введеные показания адометра,меньше предидущего путевого листа! Либо равны !" +
                    "Введите корректные показания! " );
            exeptionTypeComma.show();

            setDayKM(0);

            return;

        }
        setDayKM(dayKM);

        double dayOilRunsOut= dayKM*NormMatch/100;
        System.out.println("start number  "+dayOilRunsOut);
        BigDecimal bd = new BigDecimal(dayOilRunsOut).setScale(2, RoundingMode.HALF_EVEN);
        dayOilRunsOut=bd.doubleValue();
        System.out.println(" the end number " + dayOilRunsOut);

        double finishOil= StartOil+addOil-dayOilRunsOut;
        BigDecimal bd2 = new BigDecimal(finishOil).setScale(2,RoundingMode.HALF_EVEN);
        finishOil = bd2.doubleValue();
        System.out.println("посчитало так "+ finishOil);

        while (finishOil < 0){
            Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
            exeptionTypeComma.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma.setContentText("Внимание вы израсходовали топлива больше ,чем было на автомобиле! " +
                    "Введите верные показания! " );
            exeptionTypeComma.show();

            setDayKM(0);

            return;
        }

        while (finishOil<1){

            Alert exeptionTypeComma1 = new Alert(Alert.AlertType.ERROR);
            exeptionTypeComma1.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma1.setContentText("Внимание остаток в баке на конец смены меньше 1 литра! Уменьшите киллометраж!" );
            exeptionTypeComma1.show();

            setDayKM(0);

            return;

        }

        setDayOilRunsOut(dayOilRunsOut);
        setFinishOil(finishOil);

        System.out.println("km "+ FinishKM + " - " + StartKM + " = " + dayKM);
        System.out.println(" oil " + StartOil + " +  zapravka (" + addOil + ") - zatratili  " + dayOilRunsOut + " = "+ finishOil);


    }

    public void setDayOilRunsOut(Double dayOilRunsOut) {
        DayOilRunsOut = dayOilRunsOut;
    }

    public Double getDayOilRunsOut() {
        return DayOilRunsOut;
    }


    public void setFinishOil(Double finishOil) {
        FinishOil = finishOil;
    }

    public Double getFinishOil() {
        return FinishOil;
    }

    public void setDayKM(int dayKM) {

        DayKM = dayKM;
    }

    public int getDayKM() {
        return DayKM;
    }
}