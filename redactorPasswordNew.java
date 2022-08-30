package Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class redactorPasswordNew {
    @FXML
    private TextField PasswordText;

    @FXML
    private Button SavePassword;

    @FXML
    void initialize() {
        SavePassword.setOnAction(event -> {
            Classes.startComboboxUpdate sixnine = new  Classes.startComboboxUpdate();

            sixnine.setPasswordNew(PasswordText.getText());

            System.out.println(PasswordText.getText());
            System.out.println( sixnine.getPasswordNew());

            Alert exeptionTypeComma = new Alert(Alert.AlertType.INFORMATION);
            exeptionTypeComma.setTitle("ВНИМАНИЕ!");
            exeptionTypeComma.setContentText("Пароль изменен!" );
            exeptionTypeComma.show();

            Stage stageEdit = (Stage)SavePassword.getScene().getWindow();
            stageEdit.close();

        });

    }
}

