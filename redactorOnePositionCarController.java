package Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class redactorOnePositionCarController  {
    Classes.startComboboxUpdate one = new Classes.startComboboxUpdate();
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
    private TextField OneKillometr;

    @FXML
    private Button SaveListOne;

    @FXML
    private TextField StartOil;

    @FXML
    void initialize() {


        one.ComboboxCar();
        one.ComboboxDriver();
        one.ComboboxFuel();

        String [] Carlist = one.setCar();
        for (int b=0;b< Carlist.length;b++){
            System.out.println(Carlist [b]);

            CarScroll.getItems().add(Carlist[b]);
        }

        String [] Driverlist = one.setDriver();
        for (int b=0;b< Driverlist.length;b++){
            System.out.println(Driverlist [b]);

            DriverScroll.getItems().add(Driverlist[b]);
        }

        String [] Fuellist = one.setFuel();
        for (int b=0;b< Fuellist.length;b++){
            System.out.println(Fuellist [b]);

            FuelScroll.getItems().add(Fuellist[b]);
        }

        String ZapravkaZero = "Вид топлива не выбран";
        FuelScroll.getSelectionModel().select(ZapravkaZero);

        SaveListOne.setOnAction(event -> {
            Load.addList ListOne = new Load.addList();
            Classes.kalkulator studyListOne = new Classes.kalkulator();

            try{

//            String DataSegodnya = DateList.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalDate DateOneList = DateList.getValue();
                String CarDay = CarScroll.getValue().toString();
                String DriverDay = DriverScroll.getValue().toString();
                String KillometrStartStr = OneKillometr.getText().trim();
                String KillometrFinishStr = ManyKilometr.getText().trim();
                String FuelType = FuelScroll.getValue().toString();
                String ZapravkaLitrStr = ManyLitr.getText().trim();
                String StartOilStr = StartOil.getText().trim();

                Double ZapravkaLitr = 0.0;

                if (!DateOneList.equals("") && !CarDay.equals("") && !DriverDay.equals("") && !KillometrFinishStr.equals("")
                        && KillometrStartStr.equals("") && KillometrFinishStr.equals("") && FuelType.equals("") && StartOilStr.equals("")){
                    System.out.println("Its okay");}
                else{
                    System.out.print("dannye pystye");
                }

                int KillometrStart = Integer.parseInt(KillometrStartStr);
                System.out.println(KillometrStart);
                int KillometrFinish = Integer.parseInt(KillometrFinishStr);
                System.out.println(KillometrFinish);

                Double StartOilParse = Double.parseDouble(StartOilStr);
                System.out.println("oil start "+StartOilParse);


                if (!ZapravkaLitrStr.equals("")) {
                    Double ZapravkaLitrParse = Double.parseDouble(ZapravkaLitrStr);
                    System.out.println("zapravka oil "+ZapravkaLitrParse);
                    BigDecimal bd1 = new BigDecimal(ZapravkaLitrParse).setScale(2, RoundingMode.HALF_EVEN);
                    ZapravkaLitr = bd1.doubleValue();
                }

                BigDecimal bd2 = new BigDecimal(StartOilParse).setScale(2,RoundingMode.HALF_EVEN);
                Double StartOil = bd2.doubleValue();
                System.out.println("Pognali");
                one.CheckNormOil(CarDay,DateOneList);
                Double NormOilbylist = one.getNorm();
                System.out.println("---------------------");
                System.out.println("мы нашли норму " + NormOilbylist);

                if (NormOilbylist==0){
                    Alert exeptionTypeDateOil = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeDateOil.setTitle("ВНИМАНИЕ!");
                    exeptionTypeDateOil.setContentText("Для даты путевого листа который вы добовляете не назначена норма! " );
                    exeptionTypeDateOil.show();
                    return;
                }

                studyListOne.kalculator1(KillometrStart,KillometrFinish,StartOil,ZapravkaLitr,NormOilbylist);


                System.out.println(" достаем инфу : топливо на конец дня " +  studyListOne.getFinishOil()+
                        " пробег за день " + studyListOne.getDayKM() + " расход за день " + studyListOne.getDayOilRunsOut());

                if(studyListOne.getDayKM() > 0 ){
                    System.out.println(" go to base ");

                    ListOne.addListBase(KillometrFinish,KillometrStart, studyListOne.getDayKM(), StartOil,
                            studyListOne.getFinishOil(),studyListOne.getDayOilRunsOut(),CarDay, DriverDay, ZapravkaLitr,
                            FuelType,DateOneList);
                    Stage listone = (Stage)SaveListOne.getScene().getWindow();
                    listone.close();

                }

            }

            catch (NumberFormatException e){
                Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
                exeptionTypeComma.setTitle("ВНИМАНИЕ!");
                exeptionTypeComma.setContentText("Данные  пропущены или введены некорректно! " +
                        "При вводе дробных значений обратите внимание на ввод через точку! " );
                exeptionTypeComma.show();
            }
            catch (NullPointerException e){
                Alert listempty = new Alert(Alert.AlertType.ERROR);
                listempty.setTitle("Путевой лист Р24.by");
                listempty.setContentText("Остались пустыми поля обязательные для заполнения!");
                listempty.show();
            }
            catch (Exception e) {
                System.out.print("dont open onelist");
            }

        });
    }
}


