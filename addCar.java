package Load;

import java.sql.*;

public class addCar {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "rustam";
    public static final String URL = "jdbc:mysql://localhost:3306/list?useSSL=false&serverTimezone=Etc/GMT-3";
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
    public void addCarBase(String NameCar, String NumberCar, String GaragNumber){
        String insert = "INSERT INTO car (car_name,car_number,car_garag_number) VALUES (?,?,?)" ;
        try {
            PreparedStatement saveCar = connection.prepareStatement(insert);
            saveCar.setString(1, NameCar);
            saveCar.setString(2, NumberCar);
            saveCar.setString(3, GaragNumber);
            saveCar.executeUpdate();
            saveCar.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
