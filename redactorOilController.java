package Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class redactorOilController {

    @FXML
    private TextField Oil_Name;

    @FXML
    private Button SaveOil;

    @FXML
    void initialize (){
        Load.addOil Oil = new Load.addOil();

        SaveOil.setOnAction(event -> {
            try {
                String OilName = Oil_Name.getText().trim();
                System.out.println("user input " + OilName);
                if (!OilName.equals(" ")) {
                    Oil.addOilBase(OilName);
                } else {
                    System.out.println("input null");
                }
            } catch (Exception e) {
                System.out.println("oil dont add ");
            }

            Stage stageAddOil = (Stage)SaveOil.getScene().getWindow();
            stageAddOil.close();



        });
    }
}
