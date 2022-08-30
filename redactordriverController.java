package Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class redactordriverController {
    @FXML
    private TextField NameDriver;

    @FXML
    private Button SaveDriver;

    @FXML
    void initialize() {
        SaveDriver.setOnAction(event -> {
            Load.addDriver driver = new Load.addDriver();
            try{
                String newDriverName = NameDriver.getText().trim();

                System.out.println(newDriverName);

                if(!NameDriver.equals("")) {
                    driver.addDriverBase(newDriverName);
                }
                else {
                    System.out.println("pystye dannye");

                    Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeComma.setTitle("ВНИМАНИЕ!");
                    exeptionTypeComma.setContentText("Данные  пропущены или введены некорректно! " );
                    exeptionTypeComma.show();
                    return;
                }
            }
            catch (Exception e){
                System.out.println("driver no save");
            }


            Stage stageCreatDriver = (Stage)SaveDriver.getScene().getWindow();
            stageCreatDriver.close();


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

            Alert exeptionTypeComma = new Alert(Alert.AlertType.INFORMATION);
            exeptionTypeComma.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma.setContentText("Водитель добавлен!" );
            exeptionTypeComma.show();

        });

    }
}

