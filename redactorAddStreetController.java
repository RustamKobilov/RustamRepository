package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class redactorAddStreetController {

    @FXML
    private Button AddStreetBase;

    @FXML
    private TextField NameStreetList;

    public void initialize(){
        AddStreetBase.setOnAction(event -> {
            String NameLongStreet =   NameStreetList.getText();
            if (!NameLongStreet.equals("")) {
                String delimitr = "\\,";
                String[] StreetName;
                StreetName = NameLongStreet.split(delimitr);
                for (int x = 0; x < StreetName.length; x++) {
                    System.out.println(StreetName[x]);
                }
                Load.addStreet loadBaseStreet = new Load.addStreet();
                loadBaseStreet.AddStreet(StreetName);

                Stage AddStreetScene = (Stage)AddStreetBase.getScene().getWindow();
                AddStreetScene.close();

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


                Alert Addstreet = new Alert(Alert.AlertType.INFORMATION);
                Addstreet.setTitle("Путевой лист Р24.by");
                Addstreet.setContentText("Улица(ы) успешно добавлена(ы)!");
                Addstreet.show();
            }
            else {
                System.out.print("dannye pystye");

                Alert exeptionTypeComma = new Alert(Alert.AlertType.ERROR);
                exeptionTypeComma.setTitle("ВНИМАНИЕ!");
                exeptionTypeComma.setContentText("Данные  пропущены или введены некорректно! " );
                exeptionTypeComma.show();
                return;
            }
        });

    }


}

