package Controllers;

import Load.addCar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class redactorcarController {

    @FXML
    private TextField CarName;

    @FXML
    private TextField CarNumber;

    @FXML
    private TextField GaragNumber;

    @FXML
    private Button SaveCar;


    @FXML
    void initialize() {
        SaveCar.setOnAction(event -> {
            addCar Car = new addCar();
            System.out.println("press button");
            try {
                String newCarName = CarName.getText().trim();
                String newCarGaragNumb = GaragNumber.getText().trim();
                String newCarNumb = CarNumber.getText().trim();

                System.out.println(newCarNumb);
                System.out.println(newCarName);
                System.out.println(newCarGaragNumb);

                if(!newCarNumb.equals("") && !newCarGaragNumb.equals("") && !newCarName.equals("") ) {
                    Car.addCarBase(newCarName, newCarNumb,newCarGaragNumb);
                }
                else {
                    System.out.print("dannye pystye");

                    Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeComma.setTitle("ВНИМАНИЕ!");
                    exeptionTypeComma.setContentText("Данные  пропущены или введены некорректно! " );
                    exeptionTypeComma.show();
                    return;
                }

            }
            catch (Exception e){
                System.out.print("car no save");
            }

            Stage stageCreatcar = (Stage)SaveCar.getScene().getWindow();
            stageCreatcar.close();

            try{
                Parent root2 = FXMLLoader.load(getClass().getResource("/redactorCarTable.fxml"));
                Stage stageCreatcar1= new Stage();
                stageCreatcar1 .setTitle("Путевой лист Р24.by");
                stageCreatcar1.setScene((new Scene(root2)));
                stageCreatcar1.show();
            }catch (Exception e){
                System.out.print("cant new car");
            }

            Alert exeptionTypeComma = new Alert(Alert.AlertType.INFORMATION);
            exeptionTypeComma.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma.setContentText("Автомобиль добавлен!" );
            exeptionTypeComma.show();

        });
    }
}

