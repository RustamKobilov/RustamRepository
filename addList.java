package Load;


import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class addList {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "rustam";
    public static final String URL = "jdbc:mysql://localhost:3306/list?useSSL=false&serverTimezone=Etc/GMT-3";
    public static Statement statement;
    public static Connection connection;

    static {
        try{
            connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try{
            statement= connection.createStatement();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }




    public void addListBase(int killometrFinish, int killometrStart, int dayKillometr, double startOil, double finishOil,
                            double dayOil, String carList, String driverList, double zapravkalitr, String nameAddOil,
                            LocalDate dataSegodnya){

        LocalDate dateOil = dataSegodnya;

        System.out.println("  in base hurt");

        if(zapravkalitr<=0){
            System.out.println("voshli");
            dateOil = null;
            nameAddOil = null;
            System.out.println("vushli");
        }

        System.out.println("syda doshel");

        String insert= "INSERT INTO list (LastKillometr,StartKillometr, DayKillometr, StartOil, FinishOil," +
                "DayOil, CarList, DriverList, addOil,name_add_Oil, date,date_Oil ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            System.out.println("poshel zapros");
            System.out.println(" go to base " + dataSegodnya.toString());

            PreparedStatement addlist = connection.prepareStatement(insert);
            addlist.setInt(1, killometrFinish);
            addlist.setInt(2, killometrStart);
            addlist.setInt(3,dayKillometr);
            addlist.setDouble(4,startOil);
            addlist.setDouble(5,finishOil);
            addlist.setDouble(6,dayOil);
            addlist.setString(7,carList);
            addlist.setString(8,driverList);
            addlist.setDouble(9,zapravkalitr);
            addlist.setString(10,nameAddOil);
            addlist.setObject(11,dataSegodnya);
            addlist.setObject(12,dateOil);
            addlist.executeUpdate();
            addlist.close();

            Alert addList1 = new Alert(Alert.AlertType.INFORMATION);
            addList1.setTitle("Путевой лист Р24.by");
            addList1.setContentText("Путевой лист успешно добавлен в базу данных!");
            addList1.show();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }


}
