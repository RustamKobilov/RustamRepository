package Controllers;


import Classes.FuelNorm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class redactorOilNormTable {
    Classes.startComboboxUpdate eleven = new Classes.startComboboxUpdate();

    @FXML
    private Button AddNorm;

    @FXML
    private TableColumn FuelNorm;

    @FXML
    private TableColumn FuelType;

    @FXML
    private TableColumn dateNormActivity;

    @FXML
    private Button deleteNorm;

    @FXML
    private TableColumn CarUse;

    @FXML
    private TableView tableFuelNorm;

    @FXML
    void initialize() {

        ObservableList <FuelNorm> FuelNormTable = eleven.UserLookFuelNorm();

        dateNormActivity.setCellValueFactory(new PropertyValueFactory<FuelNorm, LocalDate>("dateStartNorm"));
        FuelNorm.setCellValueFactory(new PropertyValueFactory<FuelNorm, Double>("Norm"));
        FuelType.setCellValueFactory(new PropertyValueFactory<FuelNorm,String>("fuel"));
        CarUse.setCellValueFactory(new PropertyValueFactory<FuelNorm,String>("CarNumber"));

        tableFuelNorm.setItems(FuelNormTable);

        TableView.TableViewSelectionModel<FuelNorm> SaveNormLine = tableFuelNorm.getSelectionModel();
        SaveNormLine.selectedItemProperty().addListener(new ChangeListener<Classes.FuelNorm>() {
            @Override
            public void changed(ObservableValue<? extends Classes.FuelNorm> observable, Classes.FuelNorm oldValue, Classes.FuelNorm newValue) {

                deleteNorm.setOnAction(event -> {
                    tableFuelNorm.getItems().remove(SaveNormLine.getSelectedIndex());
                    eleven.DeleteItemPositionNorm(newValue.getFuel(),newValue.getDateStartNorm());
                });
            }
        });

        AddNorm.setOnAction(event -> {
            try {
                Parent root4 = FXMLLoader.load(getClass().getResource("/FXML/redactorOilNorm.fxml"));
                Stage stageAddoil = new Stage();
                stageAddoil.setTitle("Путевой лист Р24.by");
                stageAddoil.setScene(new Scene(root4));
                stageAddoil.show();
            } catch (Exception e) {
                System.out.print("cant load new list");
            }

            Stage stageAddOilNorm = (Stage)AddNorm.getScene().getWindow();
            stageAddOilNorm.close();

        });
    }
}


