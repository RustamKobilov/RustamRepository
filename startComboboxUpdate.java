package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class startComboboxUpdate {

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "rustam";
    public static final String URL = "jdbc:mysql://localhost:3306/list?useSSL=false&serverTimezone=Etc/GMT-3";
    //    "jdbc:mysql://localhost:3306/list?autoReconnect=true&useSSL=false";
    public static Statement statement;
    public static Connection connection;

    ArrayList<String> Driver = new ArrayList<String>();
    ArrayList<String> Car = new ArrayList<String>();
    ArrayList<String> Fuel = new ArrayList<>();

    String[] Carlist = new String[Car.size()];
    String[] Driverlist = new String[Driver.size()];
    String[] Fuellist = new String[Fuel.size()];

    Double Norm;
    Double LastOilBeforeCar;

    int LastKmBeforeCar;
    int CheckDate;
    int CoinList;

    private ObservableList<ListFull> LoadTableFull = FXCollections.observableArrayList();

    public ObservableList<ListFull> getLoadTableFull() {
        return LoadTableFull;
    }

    public void setLoadTableFull(ObservableList<ListFull> loadTableFull) {
        LoadTableFull = loadTableFull;
    }

    //    String pp = "db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboboxCar() {

        try {
            //                System.out.println("ComboboxCar start");
            String Zapros = ("SELECT car_number FROM car");
            ResultSet CarColumn = statement.executeQuery(Zapros);
            while (CarColumn.next()) {
                Car.add(CarColumn.getString("car_number"));
//                System.out.println(Car);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ComboboxDriver() {

        try {
//                System.out.println("ComboboxDriver start");

            String Zapros = ("SELECT driver_name FROM driver");
            ResultSet DriverColumn = statement.executeQuery(Zapros);
            while (DriverColumn.next()) {
                Driver.add(DriverColumn.getString("driver_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String[] setCar() {
        Carlist = Car.toArray(Carlist);
        for (String i : Carlist) {
            System.out.println(i);
        }
        ;
        return Carlist;
    }

    public String[] setDriver() {
        Driverlist = Driver.toArray(Driverlist);
        for (String i : Driverlist) {
            System.out.println(i);
        }
        ;
        return Driverlist;
    }

    public String[] setFuel() {
        Fuellist = Fuel.toArray(Fuellist);
        for (String i : Fuellist) {
            System.out.println(i);
        }
        ;
        return Fuellist;
    }

    public void ComboboxFuel() {

        try {
            System.out.println("ComboboxFuel start");
            String Zapros = ("SELECT fuelname FROM fuel");
            ResultSet FuelColumn = statement.executeQuery(Zapros);
            while (FuelColumn.next()) {
                Fuel.add(FuelColumn.getString("fuelname"));
                System.out.println(Fuel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Double getNorm() {
        return Norm;
    }

    public void setNorm(Double norm) {
        Norm = norm;
    }

    public void CheckNormOil(String NameCar, LocalDate DayList) {
        System.out.println("start proverki");

        System.out.println(DayList);


        String Zapros = ("select fuel_norm_car, date_activaty from fuel_norm where car_number = '" + NameCar + "'"
                + " order by date_activaty desc ");

        try {
            System.out.println("telo");
            ResultSet CarColumn = statement.executeQuery(Zapros);
            while (CarColumn.next()) {

                LocalDate checkDate = CarColumn.getDate(2).toLocalDate();
                int mathPoint = DayList.compareTo(checkDate);
                System.out.println("resultat v searchnorm " + mathPoint);

                if (mathPoint >= 0) {
                    setNorm(CarColumn.getDouble(1));
                    System.out.println(Norm);
                    System.out.println("_____________");
                    return;
                }

            }
            setNorm(0.0);
            System.out.println("норма не задана");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setLastKmBeforeCar(int lastKmBeforeCar) {
        LastKmBeforeCar = lastKmBeforeCar;
    }

    public int getLastKmBeforeCar() {
        return LastKmBeforeCar;
    }

    public void setLastOilBeforeCar(Double lastOilBeforeCar) {
        LastOilBeforeCar = lastOilBeforeCar;
    }

    public Double getLastOilBeforeCar() {
        return LastOilBeforeCar;
    }


    public void lastBasePosition(String CarSearch, LocalDate DateSearch) {
        setCheckDate(0);
        setCoinList(0);

        System.out.println("go baseposition");

        String Zapros = ("SELECT " + "LastKillometr, FinishOil, date" + " from list where CarList = '" + CarSearch + "'"
                + "ORDER BY date DESC limit 1");
        try {
            System.out.println("go try base position");

            ResultSet LastKmBase = statement.executeQuery(Zapros);
            while (LastKmBase.next()) {

                Integer LastKmBeforeCarBase = LastKmBase.getInt(1);

                Double LastOilBeforeCarBase = LastKmBase.getDouble(2);

                LocalDate LastDay = LastKmBase.getDate(3).toLocalDate();

                if (LastOilBeforeCarBase == null) {
                    setCoinList(0);
                    return;
                }
                if (LastKmBeforeCarBase == null) {
                    setCoinList(0);
                    return;
                }

                setCoinList(1);

                if (DateSearch.compareTo(LastDay) < 0) {
                    System.out.println(DateSearch);

                    System.out.println(LastDay);


                    setCheckDate(DateSearch.compareTo(LastDay));
                    System.out.println(" результат сравнения" + DateSearch.compareTo(LastDay));
                }


                System.out.println("LastKmBase " + CarSearch + " = " + LastKmBeforeCarBase);
                System.out.println(" LastOil = " + LastOilBeforeCarBase);

                setLastKmBeforeCar(LastKmBeforeCarBase);
                setLastOilBeforeCar(LastOilBeforeCarBase);

                System.out.println("Set work comleted");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(int checkDate) {
        CheckDate = checkDate;
    }

    public int getCoinList() {
        return CoinList;
    }

    public void setCoinList(int coinList) {
        CoinList = coinList;
    }


    public ObservableList UserLookList() {

        ObservableList<List> listview = FXCollections.observableArrayList();
        System.out.println("my na meste");
        String Zapros = ("SELECT " + "date, CarList, DriverList, StartKillometr, LastKillometr, StartOil, FinishOil , addOil" + " from list " + "ORDER BY date DESC");
        try {
            ResultSet Userlook = statement.executeQuery(Zapros);
            System.out.println("my na meste nah");
            while (Userlook.next()) {
                System.out.println("my na meste poshel potok");


                LocalDate DateTab = Userlook.getDate(1).toLocalDate();
                String CarTab = Userlook.getString(2);
                String DriverTab = Userlook.getString(3);
                Integer StartProb = Userlook.getInt(4);
                Integer FinishProb = Userlook.getInt(5);
                Double StartOilTab = Userlook.getDouble(6);
                Double FinishOilTab = Userlook.getDouble(7);
                Double AddOilTab = Userlook.getDouble(8);

//                System.out.println(DateTab);
//                System.out.println(CarTab);
//                System.out.println(DriverTab);
//                System.out.println(StartProb);
//                System.out.println(FinishProb);
//                System.out.println(StartOilTab);
//                System.out.println(FinishOilTab);
//                System.out.println(AddOilTab);

//                getLoadTable().add(new List(DateTab, CarTab, DriverTab, StartProb, FinishProb, StartOilTab, FinishOilTab, AddOilTab));
                listview.addAll(new List(DateTab, CarTab, DriverTab, StartProb, FinishProb, StartOilTab, FinishOilTab, AddOilTab));
            }
            System.out.println();
            System.out.println("konets");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listview;
    }


    public void setPasswordNew(String PASSWORD) {
        String Zapros = "Insert into password  (PasswordNow) VALUES (?)";
        try {
            PreparedStatement SavePassword = connection.prepareStatement(Zapros);
            SavePassword.setString(1, PASSWORD);
            SavePassword.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String getPasswordNew() {
        String passwordlast = null;
        String Zapros = "select PasswordNow  from password ORDER BY idpassword DESC limit 1";
        try {
            ResultSet PasswordLast = statement.executeQuery(Zapros);
            while (PasswordLast.next()) {
                passwordlast = PasswordLast.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return passwordlast;
    }


    public ObservableList UserLookDriver() {
        ObservableList<Classes.Driver> DriverBase = FXCollections.observableArrayList();
        try {
            String Zapros = ("SELECT driver_name FROM driver");
            ResultSet DriverColumn = statement.executeQuery(Zapros);
            while (DriverColumn.next()) {
//                Driver.add(DriverColumn.getString("driver_name"));
                String driver = DriverColumn.getString("driver_name");
                DriverBase.addAll(new Driver(driver));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return DriverBase;
    }


    public void DeleteItemPositionlist(LocalDate DayList, String CarList, String DriverList, Double StartOilList) {

        String Zapros = "DElETE from list where CarList = '" + CarList + "' and date = '" + DayList + "' and DriverList = '" + DriverList
                + "' and StartOil = " + StartOilList;
        try {
            PreparedStatement DeleteList = connection.prepareStatement(Zapros);
            DeleteList.executeUpdate();
            System.out.println("try dleteitem the end");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteItemPositionDriver(String Name) {
        String Zapros = "DELETE from driver where driver_name = '" + Name + "'";
        try {
            PreparedStatement DeleteDriver = connection.prepareStatement(Zapros);
            DeleteDriver.executeUpdate();
            System.out.println("try delete driver");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteItemPositionCar(String NameCar) {
        String zapros = " DELETE from car where car_number = '" + NameCar + "'";
        try {
            PreparedStatement DeleteCar = connection.prepareStatement(zapros);
            DeleteCar.executeUpdate();
            System.out.println("delete car complete");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void DeleteItemPositionNorm(String FuelType, LocalDate dateStartNorm) {
        String zapros = "DELETE from fuel_norm where fuel_type = '" + FuelType + "' and date_activaty = '" + dateStartNorm + "'";
        try {
            PreparedStatement deleteNorm = connection.prepareStatement(zapros);
            deleteNorm.executeUpdate();
            System.out.println("delete norm complete");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ObservableList UserLookCar() {
        ObservableList<Classes.Car> listCarView = FXCollections.observableArrayList();
        String Zapros = ("SELECT " + "car_name, car_number, car_garag_number" + " from car " + "ORDER BY idcar DESC");

        try {
            ResultSet Userlook = statement.executeQuery(Zapros);
            System.out.println("my na meste nah");
            while (Userlook.next()) {
                System.out.println("my na meste poshel potok");

                String Carmodel = Userlook.getString(1);
                String Carname = Userlook.getString(2);
                String Cargarag = Userlook.getString(3);

                listCarView.addAll(new Car(Carname, Carmodel, Cargarag));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listCarView;
    }

    public ObservableList UserLookFuelNorm() {
        ObservableList<FuelNorm> FuelNormView = FXCollections.observableArrayList();
        String zapros = ("SELECT fuel_type, date_activaty,  car_number, fuel_norm_car from fuel_norm ORDER BY id_fuel_norm DESC");
        try {
            ResultSet FuelNormResult = statement.executeQuery(zapros);
            while (FuelNormResult.next()) {

                String TypeFuel = FuelNormResult.getString(1);
                LocalDate ActivateNorm = FuelNormResult.getDate(2).toLocalDate();
                String CarForNorm = FuelNormResult.getString(3);
                Double Norm = FuelNormResult.getDouble(4);

                FuelNormView.addAll(new FuelNorm(TypeFuel, ActivateNorm, CarForNorm, Norm));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FuelNormView;
    }

    public void UpListFull(String CarName, String DriverName, LocalDate DayListItem, Integer StartKM) {
        String Zapros = ("SELECT " + " DayOil,DayKillometr, name_add_Oil from list where date = '" + DayListItem + "' and CarList = '" + CarName +
                "' and DriverList = '" + DriverName + "' and StartKillometr = '" + StartKM + "'");

        try {
            System.out.println(CarName + " car name ");
            System.out.println(DriverName + " drivername");
            System.out.println(DayListItem.toString() + " date");
            System.out.println(StartKM + "start KM");

            ResultSet listresult = statement.executeQuery(Zapros);
            while (listresult.next()) {

                Double DayOilBase = listresult.getDouble(1);
                Integer DayKillometr = listresult.getInt(2);
                String NameAddOilBase = listresult.getString(3);
                System.out.println("poiuy");
                System.out.println(DayOilBase);
                System.out.println(DayKillometr);
                System.out.println(NameAddOilBase);


                LoadTableFull.addAll(new ListFull(DayOilBase, DayKillometr, NameAddOilBase));

                System.out.println(LoadTableFull.size());

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Classes.StreetMogilev> StreetLook() {
        ObservableList<Classes.StreetMogilev> StreetLookList = FXCollections.observableArrayList();
        String zapros = "Select StreetMogilev from street_mogilev order by idStreetMogilev desc";

        try {
            ResultSet streetMogilev = statement.executeQuery(zapros);
            while (streetMogilev.next()) {
                String Street1 = streetMogilev.getString(1);

                StreetLookList.addAll(new StreetMogilev(Street1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return StreetLookList;
    }

    public void DeleteItemPositionStreetinBase(String NameStret) {
        String zapros = "delete from street_mogilev where StreetMogilev = '" + NameStret + "'";
        try {
            PreparedStatement deleteStreet = connection.prepareStatement(zapros);
            deleteStreet.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

