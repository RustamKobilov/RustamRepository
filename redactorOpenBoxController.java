package Controllers;

import Classes.Car;

import Classes.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;

public class redactorOpenBoxController  {

    Classes.startComboboxUpdate fore = new Classes.startComboboxUpdate();

//    @FXML
//    private Button RefreshTable;

    @FXML
    private Button deleteList;

    @FXML
    private TableView <List>tableList;

////    public TableView<List> getTableList() {
////        return tableList;
////    }

    @FXML
    private TableColumn AddOilBox;

    @FXML
    private TableColumn CarBox;

    @FXML
    private TableColumn DateBox;

    @FXML
    private TableColumn DriverBox;

    @FXML
    private TableColumn FinishKMBox;

    @FXML
    private TableColumn FinishOilBox;

    @FXML
    private TableColumn StartKMBox;

    @FXML
    private TableColumn StartOilBox;

    @FXML
    private Button addListButton;

    @FXML
    private Button PrintListButton;


    @FXML
    void initialize() {
        System.out.println("initi");

        ObservableList<List> listbang=  fore.UserLookList();
        System.out.println("2");
        System.out.println("size observable "+listbang.size());


        DateBox.setCellValueFactory(new PropertyValueFactory<List,LocalDate>("DateList"));
        DriverBox.setCellValueFactory(new PropertyValueFactory<List,String >("DriverList"));
        CarBox.setCellValueFactory(new PropertyValueFactory<List,String>("CarList"));
        StartKMBox.setCellValueFactory(new PropertyValueFactory<List,Integer>("StartProbegList"));
        FinishKMBox.setCellValueFactory(new PropertyValueFactory<List,Integer>("FinishProbegList"));
        StartOilBox.setCellValueFactory(new PropertyValueFactory<List,Double>("StartOilList"));
        FinishOilBox.setCellValueFactory(new PropertyValueFactory<List,Double>("FinishOilList"));
        AddOilBox.setCellValueFactory(new PropertyValueFactory<List,Double>("AddOilList"));

        tableList.setItems(listbang);

        TableView.TableViewSelectionModel<List> SaveLineSelected = tableList.getSelectionModel();
        SaveLineSelected.selectedItemProperty().addListener(new ChangeListener<List>() {
            @Override
            public void changed(ObservableValue<? extends List> observable, List oldValue, List newValue) {

                deleteList.setOnAction(event -> {

                    System.out.println("button delete go ");

                    fore.DeleteItemPositionlist(newValue.getDateList(), newValue.getCarList(), newValue.getDriverList(), newValue.getStartOilList());
                    tableList.getItems().remove(SaveLineSelected.getSelectedIndex());

                });

                PrintListButton.setOnAction(event1 ->{
                    System.out.println("button press print");
                    fore.UpListFull(newValue.getCarList(),newValue.getDriverList(),newValue.getDateList(),newValue.getStartProbegList());

                    System.out.println(fore.getLoadTableFull().size());
                    ObservableList<Classes.ListFull> bang2 = fore.getLoadTableFull();
                    System.out.println(bang2.size());

                    String CarGarag = "garag";
                    String CarModel = "model";

                    ObservableList<Car> listCar2 = fore.UserLookCar();
                    for (int x=0;x<listCar2.size();x++){
                        System.out.println(listCar2.get(x).getNameCar() + " poshli dannye");
                        System.out.println(newValue.getCarList() + " avto v yetot den");
                        if (listCar2.get(x).getNameCar().equals(newValue.getCarList())){
                            System.out.println("зашли в круг");
                            CarGarag = listCar2.get(x).getGaragNumberCar();
                            CarModel = listCar2.get(x).getModelCar();
                            break;
                        }
                    }
                    System.out.println("garagnumber posle search "+ CarGarag);
                    System.out.println("carmodel posle serch " + CarModel);




                    ExcelWork.ViewListController onePrint = new ExcelWork.ViewListController();

                    try {
                        onePrint.ExcelLoad(newValue.getFinishProbegList(), newValue.getStartProbegList(), bang2.get(0).getDayKillometr(),
                                newValue.getStartOilList(), newValue.getFinishOilList(), bang2.get(0).getDayOil(), newValue.getCarList(), newValue.getDriverList(),
                                newValue.getAddOilList(), bang2.get(0).getNameAddOil(), newValue.getDateList(), CarGarag, CarModel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    onePrint.PrintPytevoiList();

                } );
            }
        });


        return;
    }


}


