package Controllers;

import Classes.Car;

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

public class redactorCarTableController  {
    Classes.startComboboxUpdate each = new Classes.startComboboxUpdate();

    @FXML
    private Button AddCar;

    @FXML
    private Button deleteCar;

    @FXML
    private TableColumn garagNumberCar;

    @FXML
    private TableColumn modelCar;

    @FXML
    private TableColumn numberCar;

    @FXML
    private TableView tableCar;


    @FXML
    void initialize (){

        ObservableList <Car> CarInformation = each.UserLookCar();
        System.out.println("Observable complete");

        numberCar.setCellValueFactory(new PropertyValueFactory<Car,String>("nameCar"));
        modelCar.setCellValueFactory(new PropertyValueFactory<Car,String>("modelCar"));
        garagNumberCar.setCellValueFactory(new PropertyValueFactory<Car,String>("garagNumberCar"));

        tableCar.setItems(CarInformation);

        TableView.TableViewSelectionModel<Car> SaveLineCar = tableCar.getSelectionModel();
        SaveLineCar.selectedItemProperty().addListener(new ChangeListener<Car>() {
            @Override
            public void changed(ObservableValue<? extends Car> observable, Car oldValue, Car newValue) {
                deleteCar.setOnAction(event -> {

                    tableCar.getItems().remove(SaveLineCar.getSelectedIndex());
                    each.DeleteItemPositionCar(newValue.getNameCar());

                    Alert deletecar = new Alert(Alert.AlertType.INFORMATION);
                    deletecar.setTitle("Путевой лист Р24.by");
                    deletecar.setContentText("Машина удалена!");
                    deletecar.show();

                });
            }
        });

        AddCar.setOnAction(event -> {
            System.out.println("hi there");

            try{
                Parent root21 = FXMLLoader.load(getClass().getResource("/redactorcar.fxml"));
                Stage stageCreatcar= new Stage();
                stageCreatcar .setTitle("Путевой лист Р24.by");
                stageCreatcar.setScene((new Scene(root21)));
                stageCreatcar.show();
            }catch (Exception e){
                System.out.print("cant new car");
            }

            Stage stageAddCar = (Stage) AddCar.getScene().getWindow();
            stageAddCar.close();
        });


    }
}
