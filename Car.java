package Classes;

public class Car {
    private String nameCar;
    private String modelCar;
    private String garagNumberCar;

    public Car(String nameCar, String modelCar, String garagNumberCar){
        this.nameCar = nameCar;
        this.modelCar = modelCar;
        this.garagNumberCar = garagNumberCar;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getGaragNumberCar() {
        return garagNumberCar;
    }

    public void setGaragNumberCar(String garagNumberCar) {
        this.garagNumberCar = garagNumberCar;
    }
}
