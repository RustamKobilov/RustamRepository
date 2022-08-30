package Controllers;



import Classes.Password;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class redactorPasswordController {

    @FXML
    private TextField PasswordText;

    @FXML
    private Button EnterAdmin;

    @FXML
    void initialize() {

        EnterAdmin.setOnAction(event ->{

            try {
                Classes.startComboboxUpdate nine = new  Classes.startComboboxUpdate();
                Password onePassword = new Password();

                String UserInputPassword = PasswordText.getText();

                System.out.println(nine.getPasswordNew() + " password now");

                if (!UserInputPassword.equals(nine.getPasswordNew()) & !UserInputPassword.equals(onePassword.getPasswordBase())) {
                    Alert exeptionTypeEmpty = new Alert(Alert.AlertType.ERROR);
                    exeptionTypeEmpty.setTitle("ВНИМАНИЕ!");
                    exeptionTypeEmpty.setContentText("Неверный пароль!");
                    exeptionTypeEmpty.show();
                    return;

                }

                Stage stagePasswordStart = (Stage)EnterAdmin.getScene().getWindow();
                stagePasswordStart.close();


                try {
                    Parent root5 = FXMLLoader.load(getClass().getResource("/redactorAdmin.fxml"));
                    Stage stageOnelist = new Stage();
                    stageOnelist.setTitle("Путевой лист Р24.by");
                    stageOnelist.setScene(new Scene(root5));
                    stageOnelist.show();
                }
                catch (Exception e){
                    System.out.println("admin no activiti");
                }
            }
            catch (NullPointerException e){
                Alert exeptionTypeEmpty = new Alert(Alert.AlertType.ERROR);
                exeptionTypeEmpty.setTitle("ВНИМАНИЕ!");
                exeptionTypeEmpty.setContentText("Поле обязательно для заполнения! Будьте внимательны!");
                exeptionTypeEmpty.show();
            }


        });

    }



}

