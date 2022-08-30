package Controllers;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import Classes.Car;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class redactorlistController {

    Classes.startComboboxUpdate ty= new Classes.startComboboxUpdate();

    @FXML
    private ComboBox CarScroll;

    @FXML
    private DatePicker DateList;

    @FXML
    private ComboBox DriverScroll;

    @FXML
    private ComboBox FuelScroll;

    @FXML
    private TextField ManyKilometr;

    @FXML
    private TextField ManyLitr;

    @FXML
    private Button SaveList;

    @FXML
    public void initialize() {

        ty.ComboboxCar();
        ty.ComboboxDriver();
        ty.ComboboxFuel();

        String [] Carlist =ty.setCar();
        for (int b=0;b< Carlist.length;b++){
            System.out.println(Carlist [b]);

            CarScroll.getItems().add(Carlist[b]);
        }

        String [] Driverlist = ty.setDriver();
        for (int b=0;b< Driverlist.length;b++){
            System.out.println(Driverlist [b]);

            DriverScroll.getItems().add(Driverlist[b]);
        }

        String [] Fuellist = ty.setFuel();
        for (int b=0;b< Fuellist.length;b++){
            System.out.println(Fuellist [b]);

            FuelScroll.getItems().add(Fuellist[b]);
        }
        String ZapravkaZero = "Вид топлива не выбран";
        FuelScroll.getSelectionModel().select(ZapravkaZero);




        SaveList.setOnAction(event -> {
            Load.addList List = new Load.addList();
            Classes.kalkulator studyList = new Classes.kalkulator();

            try {
//
                LocalDate dateListUser = DateList.getValue();
                System.out.println(dateListUser.toString());

//               Date dateList = Date.valueOf(dateListUser);
//               String DataSegodnya = DateList.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String CarDay = CarScroll.getValue().toString();
                String DriverDay = DriverScroll.getValue().toString();

                String KillometrFinishStr = ManyKilometr.getText().trim();

                String FuelType = FuelScroll.getValue().toString();

                String ZapravkaLitrStr = ManyLitr.getText().trim();
                Double ZapravkaLitr = 0.0;

                if (!dateListUser.equals("") && !CarDay.equals("") && !DriverDay.equals("") && !KillometrFinishStr.equals("")) {
                    System.out.println("Its okay");
                }

                System.out.println("++++++++++++++++++++++++++");
                if (!ZapravkaLitrStr.equals("")) {
                    double ZapravkaLitrD = Double.parseDouble(ZapravkaLitrStr);
                    System.out.println(ZapravkaLitr);

                    BigDecimal bd3 = new BigDecimal(ZapravkaLitrD).setScale(2, RoundingMode.HALF_EVEN);

                    ZapravkaLitr=bd3.doubleValue();
                }
                System.out.println("==========================");

                int KillometrFinish = Integer.parseInt(KillometrFinishStr);
                System.out.println(KillometrFinish);


                ty.CheckNormOil(CarDay,dateListUser);
                Double NormOilbylist = ty.getNorm();
                System.out.println("---------------------");
                System.out.println("мы нашли норму " + NormOilbylist);

                System.out.println("---------------------");
                ty.lastBasePosition(CarDay,dateListUser);


                System.out.println(dateListUser.toString());

                if (ty.getCoinList()<1){
                    Alert exeptionNumberList = new Alert(Alert.AlertType.ERROR);
                    exeptionNumberList.setTitle("ВНИМАНИЕ!");
                    exeptionNumberList.setContentText(" В базе нет путевых для данного автомобиля! " );
                    exeptionNumberList.show();
                    return;
                }

                System.out.println(dateListUser.toString());

                if (ty.getCheckDate()<0){
                    Alert exeptionTypeDate = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeDate.setTitle("ВНИМАНИЕ!");
                    exeptionTypeDate.setContentText("Дата путевого листа который вы добовляете раньше чем дата " +
                            "последнего путевого листа в базе данных! " );
                    exeptionTypeDate.show();

                    return;
                }

                if (NormOilbylist==0){
                    Alert exeptionTypeDateOil = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeDateOil.setTitle("ВНИМАНИЕ!");
                    exeptionTypeDateOil.setContentText("Для даты путевого листа который вы добовляете не назначена норма! " );
                    exeptionTypeDateOil.show();
                    return;
                }

                System.out.println(dateListUser.toString());

                Double LastOilCarbylist = ty.getLastOilBeforeCar();
                int LastKmCarbyList=ty.getLastKmBeforeCar();
                System.out.println("dвот они последние показания адометра "+ LastKmCarbyList + " от оно топливо "+ LastOilCarbylist);
                studyList.kalculator1(LastKmCarbyList,KillometrFinish,LastOilCarbylist,ZapravkaLitr,NormOilbylist);


                System.out.println(" достаем инфу : топливо на конец дня " +  studyList.getFinishOil()+
                        " пробег за день " + studyList.getDayKM() + " расход за день " + studyList.getDayOilRunsOut());

                System.out.println(dateListUser.toString());

                if(studyList.getDayKM() > 0 ){
                    System.out.println(" go to base " + dateListUser.toString());

                    List.addListBase(KillometrFinish, LastKmCarbyList, studyList.getDayKM(), LastOilCarbylist,
                            studyList.getFinishOil(),studyList.getDayOilRunsOut(),CarDay, DriverDay, ZapravkaLitr,
                            FuelType,dateListUser);

                    System.out.println(" go print ");

                    String CarGarag = "garag";
                    String CarModel = "model";

                    ObservableList<Car> listCar2 = ty.UserLookCar();
                    for (int x=0;x<listCar2.size();x++){
                        System.out.println(listCar2.get(x).getNameCar() + " poshli dannye");
                        System.out.println(CarDay + " avto v yetot den");
                        if (listCar2.get(x).getNameCar().equals(CarDay)){
                            System.out.println("зашли в круг");
                            CarGarag = listCar2.get(x).getGaragNumberCar();
                            CarModel = listCar2.get(x).getModelCar();
                            break;
                        }
                    }
                    System.out.println("garagnumber posle search "+ CarGarag);
                    System.out.println("carmodel posle serch " + CarModel);


                    ExcelWork.ViewListController PrintNowList = new ExcelWork.ViewListController();
                    PrintNowList.ExcelLoad(KillometrFinish, LastKmCarbyList, studyList.getDayKM(), LastOilCarbylist,
                            studyList.getFinishOil(),studyList.getDayOilRunsOut(),CarDay, DriverDay, ZapravkaLitr,
                            FuelType,dateListUser,CarGarag,CarModel);
                    PrintNowList.PrintPytevoiList();

                }


            }
            catch (NumberFormatException e){
                Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
                exeptionTypeComma.setTitle("ВНИМАНИЕ!");
                exeptionTypeComma.setContentText("Данные  пропущены или введены некорректно! " );
                exeptionTypeComma.show();
            }
            catch (NullPointerException e){
                Alert listempty = new Alert(Alert.AlertType.ERROR);
                listempty.setTitle("Путевой лист Р24.by");
                listempty.setContentText("Остались пустыми поля обязательные для заполнения!");
                listempty.show();
                e.getMessage();
            }
            catch (Exception e) {
                System.out.print("vnimanie est nekarrektnoye pole");
                System.out.println(e.getStackTrace());
            }
        });

    }
}






