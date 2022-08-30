package Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class redactorDriverTableController {
    Classes.startComboboxUpdate seven = new Classes.startComboboxUpdate();

    @FXML
    private Button AddDriver;

    @FXML
    private Button deleteDriver;

    @FXML
    private TableColumn nameDriver;

    @FXML
    private TableView<Classes.Driver> tableDriver;

    @FXML
    void initialize() {

        ObservableList<Classes.Driver> DriverName = seven.UserLookDriver();
        System.out.println("Observable complete");

        nameDriver.setCellValueFactory(new PropertyValueFactory<Classes.Driver,String>("Name"));
        tableDriver.setItems(DriverName);

        TableView.TableViewSelectionModel<Classes.Driver> SaveLineDriver = tableDriver.getSelectionModel();
        SaveLineDriver.selectedItemProperty().addListener(new ChangeListener<Classes.Driver>() {
            @Override
            public void changed(ObservableValue<? extends Classes.Driver> observable, Classes.Driver oldValue, Classes.Driver newValue) {
                deleteDriver.setOnAction(event ->{
                    System.out.println("button delete press");
                    tableDriver.getItems().remove(SaveLineDriver.getSelectedIndex());
                    seven.DeleteItemPositionDriver(newValue.getName());

                    Alert deletedriver = new Alert(Alert.AlertType.INFORMATION);
                    deletedriver.setTitle("Путевой лист Р24.by");
                    deletedriver.setContentText("Водитель удален!");
                    deletedriver.show();

                });
            }
        });

        AddDriver.setOnAction(event1 -> {
            System.out.println("press button");

            try{
                Parent root3 = FXMLLoader.load(getClass().getResource("/redactordriver.fxml"));
                Stage stageCreatdriver= new Stage();
                stageCreatdriver .setTitle("Путевой лист Р24.by");
                stageCreatdriver.setScene((new Scene(root3)));
                stageCreatdriver.show();
            }catch (Exception e){
                System.out.print("cant new driver");
            }

            Stage stageAddDriver = (Stage)AddDriver.getScene().getWindow();
            stageAddDriver.close();

        });
    }
}



