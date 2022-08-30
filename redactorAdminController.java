package Controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class redactorAdminController {

    @FXML
    private Button AddOil;

    @FXML
    private Button EditPassword;

    @FXML
    private Button addNewOilNorm;

    @FXML
    private Button addOneListNewCar;

    @FXML
    private Button carOpen;

    @FXML
    private Button driverOpen;

    @FXML
    private Button newStreetButton;


    @FXML
    void initialize() {

        addOneListNewCar.setOnAction(event -> {
            try{
                Parent root8 = FXMLLoader.load(getClass().getResource("/redactorOnePositionCar.fxml"));
                Stage stageListone = new Stage();
                stageListone.setTitle("Путевой лист Р24.by");
                stageListone.setScene(new Scene(root8));
                stageListone.show();
            }
            catch (Exception e){
                System.out.println("no open stage onelistnewcar");
            }
        });


        carOpen.setOnAction(event -> {
            try{
                Parent root25 = FXMLLoader.load(getClass().getResource("/redactorCarTable.fxml"));
                Stage stageCarLine= new Stage();
                stageCarLine .setTitle("Путевой лист Р24.by");
                stageCarLine.setScene((new Scene(root25)));
                stageCarLine.show();
            }catch (Exception e){
                System.out.print("hui car");
            }
        });


        AddOil.setOnAction(event ->{
            try {
                Parent root32 = FXMLLoader.load(getClass().getResource("/redactoroil.fxml"));
                Stage stageAddoil = new Stage();
                stageAddoil.setTitle("Путевой лист Р24.by");
                stageAddoil.setScene(new Scene(root32));
                stageAddoil.show();
            } catch (Exception e) {
                System.out.print("cant load add oil");
            }
        });


        addNewOilNorm.setOnAction(event -> {
            try{
                Parent root30 = FXMLLoader.load(getClass().getResource("/redactorOilNormTable.fxml"));
                Stage stageOilNorm= new Stage();
                stageOilNorm.setTitle("Путевой лист Р24.by");
                stageOilNorm.setScene(new Scene(root30));
                stageOilNorm.show();
                stageOilNorm.setAlwaysOnTop(true);
            }
            catch (Exception e){
                System.out.println("no open Oil norm");
            }
        });

        EditPassword.setOnAction(event ->{
            try{
                Parent root10= FXMLLoader.load(getClass().getResource("/redactorPasswordNew.fxml"));
                Stage PasswordStage = new Stage();
                PasswordStage.setTitle("Путевой лист Р24.by");
                PasswordStage.setScene(new Scene(root10));
                PasswordStage.show();
            }
            catch (Exception e ){
                e.printStackTrace();
            }

        });

        driverOpen.setOnAction(event1 -> {
            try{
                Parent root19 = FXMLLoader.load(getClass().getResource("/redactorDriverTable.fxml"));
                Stage driverOpen = new Stage();
                driverOpen.setTitle("Путевой лист Р24.by");
                driverOpen.setScene(new Scene(root19));
                driverOpen.show();
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("ne zahodit");
            }
        });

        newStreetButton.setOnAction(event -> {

            try{
                Parent root25= FXMLLoader.load(getClass().getResource("/redactorStreet.fxml"));
                Stage AddStreet = new Stage();
                AddStreet.setTitle("Путевой лист Р24.by");
                AddStreet.setScene(new Scene(root25));
                AddStreet.show();
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        });
    }
}

