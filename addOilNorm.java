package Load;

import java.sql.*;
import java.time.LocalDate;

public class addOilNorm {

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

    void addOilNormBase(LocalDate dateStartNorm, Double Normvalue, String Fueltype, String [] Car) {

        try{
            for (int x=0; x< Car.length;x++){
                System.out.println(Car[x]);
                String insert = "INSERT INTO fuel_norm (fuel_norm_car,date_activaty,fuel_type,car_number) VALUES (?,?,?,?)";
                PreparedStatement addOilNorm = connection.prepareStatement(insert);
                addOilNorm.setDouble(1,Normvalue);
                addOilNorm.setObject(2,dateStartNorm);
                addOilNorm.setString(3,Fueltype);
                addOilNorm.setString(4,Car[x]);
                addOilNorm.executeUpdate();
                addOilNorm.close();
            }

        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}

