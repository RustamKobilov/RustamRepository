package Load;

import java.sql.*;

public class addStreet {

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

    public void AddStreet(String[]Street){

        try {

            String insert = "insert into street_mogilev (StreetMogilev) VALUES (?) ";

            for(int x=0;x<Street.length;x++) {
                PreparedStatement AddStreet = connection.prepareStatement(insert);
                AddStreet.setString(1, Street[x]);
                AddStreet.executeUpdate();
                AddStreet.close();
            }

        }
        catch (SQLException throwbles){
            throwbles.printStackTrace();
        }

    }

}