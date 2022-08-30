package Load;

import javafx.scene.control.Alert;

import java.sql.*;

public class addOil  {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "rustam";
    public static final String URL ="jdbc:mysql://localhost:3306/list?useSSL=false&serverTimezone=Etc/GMT-3";
    public static Statement statment;
    public static Connection connection;

    static {
        try{
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try{
            statment = connection.createStatement();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void addOilBase(String Name_Oil) {
        String insert = "INSERT INTO fuel (fuelname) VALUES (?)";
        try {
            PreparedStatement saveOil = connection.prepareStatement(insert);
            saveOil.setString(1, Name_Oil);
            saveOil.executeUpdate();
            saveOil.close();

            Alert addOil = new Alert(Alert.AlertType.INFORMATION);
            addOil.setTitle("Путевой лист Р24.by");
            addOil.setContentText("Вид топлива добавлен в базу данных!");
            addOil.show();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("add oil no base");
        }
    }
}