package Classes;

import java.time.LocalDate;

public class List {

    private LocalDate DateList;
    private String CarList;
    private String DriverList;
    private Integer StartProbegList;
    private Integer FinishProbegList;
    private Double StartOilList;
    private Double FinishOilList;
    private Double AddOilList;


    public List(LocalDate DateList, String CarList, String DriverList, Integer StartProbegList, Integer FinishProbegList,
                Double StartOilList, Double FinishOilList,  Double AddOilList){
        this.DateList = DateList;
        this.CarList =  CarList;
        this.DriverList = DriverList;
        this.StartProbegList = StartProbegList;
        this.FinishProbegList = FinishProbegList;
        this.StartOilList = StartOilList;
        this.FinishOilList = FinishOilList;
        this.AddOilList = AddOilList;

    }


    public LocalDate getDateList() {
        return DateList;
    }

    public String getCarList() {
        return CarList;
    }

    public String getDriverList() {
        return DriverList;
    }

    public Integer getStartProbegList() {
        return StartProbegList;
    }

    public Integer getFinishProbegList() {
        return FinishProbegList;
    }

    public Double getStartOilList() {
        return StartOilList;
    }

    public Double getFinishOilList() {
        return FinishOilList;
    }

    public Double getAddOilList() {
        return AddOilList;
    }

    public void setDateList(LocalDate dateList) {
        DateList = dateList;
    }

    public void setCarList(String carList) {
        CarList = carList;
    }

    public void setDriverList(String driverList) {
        DriverList = driverList;
    }

    public void setStartProbegList(Integer startProbegList) {
        StartProbegList = startProbegList;
    }

    public void setFinishProbegList(Integer finishProbegList) {
        FinishProbegList = finishProbegList;
    }

    public void setStartOilList(Double startOilList) {
        StartOilList = startOilList;
    }

    public void setFinishOilList(Double finishOilList) {
        FinishOilList = finishOilList;
    }

    public void setAddOilList(Double addOilList) {
        AddOilList = addOilList;
    }
}