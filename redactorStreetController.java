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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class redactorStreetController {

    Classes.startComboboxUpdate eleven = new Classes.startComboboxUpdate();
    @FXML
    private Button AddStreet;

    @FXML
    private Button deleteStreet;

    @FXML
    private TableColumn nameStreet;

    @FXML
    private TableView <Classes.StreetMogilev> tableStreet;

    @FXML
    public void initialize(){
        ObservableList<Classes.StreetMogilev> Street = eleven.StreetLook();
        System.out.println(Street.size() + "Размер ");

        nameStreet.setCellValueFactory(new PropertyValueFactory<Classes.StreetMogilev,String>("Street"));
        tableStreet.setItems(Street);

        TableView.TableViewSelectionModel<Classes.StreetMogilev> StreetTableModel = tableStreet.getSelectionModel();
        StreetTableModel.selectedItemProperty().addListener(new ChangeListener<Classes.StreetMogilev>() {
            @Override
            public void changed(ObservableValue<? extends Classes.StreetMogilev> observable, Classes.StreetMogilev oldValue, Classes.StreetMogilev newValue) {
                deleteStreet.setOnAction(event -> {
                    eleven.DeleteItemPositionStreetinBase(newValue.getStreet());
                    tableStreet.getItems().remove(StreetTableModel.getSelectedIndex());

                    Alert deletestreet = new Alert(Alert.AlertType.INFORMATION);
                    deletestreet.setTitle("Путевой лист Р24.by");
                    deletestreet.setContentText("Улица удалена!");
                    deletestreet.show();
                });
            }
        });


        AddStreet.setOnAction(event -> {

            try{
                Parent root20 = FXMLLoader.load(getClass().getResource("/redactorAddStreet.fxml"));
                Stage stageAddStreet = new Stage();
                stageAddStreet.setTitle("Путевой лист Р24.by");
                stageAddStreet.setScene((new Scene(root20)));
                stageAddStreet.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            Stage AddStreetScene2 = (Stage)AddStreet.getScene().getWindow();
            AddStreetScene2.close();
        });


    }

}

