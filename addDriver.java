package Load;

import java.sql.*;

public class addDriver {
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

    public void addDriverBase(String LastName) {
        String insert = "INSERT INTO driver (driver_name) VALUES (?)" ;
        try {
            PreparedStatement saveDriver = connection.prepareStatement(insert);
            saveDriver.setString(1, LastName);
            saveDriver.executeUpdate();
            saveDriver.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
