package Classes;

import java.time.LocalDate;

public class FuelNorm {
    private String fuel;
    private LocalDate dateStartNorm;
    private String CarNumber;
    private Double Norm;

    public FuelNorm(String fuel,LocalDate dataStartNorm, String CarNumber, Double Norm){
        this.fuel= fuel;
        this.dateStartNorm = dataStartNorm;
        this.CarNumber = CarNumber;
        this.Norm = Norm;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public LocalDate getDateStartNorm() {
        return dateStartNorm;
    }

    public void setDateStartNorm(LocalDate dateStartNorm) {
        this.dateStartNorm = dateStartNorm;
    }

    public String getCarNumber() {
        return CarNumber;
    }

    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }

    public Double getNorm() {
        return Norm;
    }

    public void setNorm(Double norm) {
        Norm = norm;
    }
}

