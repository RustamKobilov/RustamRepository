
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class Controller {

    @FXML
    private Button CreateList;

    @FXML
    private Button OpenBoxList;

    @FXML
    private Button OpenModeAdmin;

//    @FXML
//    private Button TestButton;

    @FXML
    void initialize() {
        CreateList.setOnAction(event -> {

            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/redactorlist.fxml"));
                Stage stageSavelist = new Stage();
                stageSavelist.setTitle("Путевой лист Р24.by");
                stageSavelist.setScene(new Scene(root1));
                stageSavelist.show();
            } catch (Exception e) {
                System.out.print("cant load new list");
            }
        });


        OpenBoxList.setOnAction(event -> {

            try {
                Parent root2 = FXMLLoader.load(getClass().getResource("/redactorOpenBox.fxml"));
                Stage stageBoxList = new Stage();
                stageBoxList.setTitle("Путевой лист Р24.by");
                stageBoxList.setScene(new Scene(root2));
                stageBoxList.show();
            } catch (Exception e) {
                System.out.print("cant load new openboxlist");
            }
        });



        OpenModeAdmin.setOnAction(event -> {

            try{
                Parent root10= FXMLLoader.load(getClass().getResource("/redactorPassword.fxml"));
                Stage PasswordStage = new Stage();
                PasswordStage.setTitle("Путевой лист Р24.by");
                PasswordStage.setScene(new Scene(root10));
                PasswordStage.show();
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        });

    }


}

