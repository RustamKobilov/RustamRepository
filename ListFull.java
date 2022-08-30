package Classes;

public class ListFull {

    private double DayOil;
    private Integer DayKillometr;
    private String NameAddOil;


    ListFull(double DayOil, Integer DayKillometr, String NameAddOil){
        this.DayOil = DayOil;
        this.DayKillometr = DayKillometr;
        this.NameAddOil = NameAddOil;

    }

    public double getDayOil() {
        return DayOil;
    }

    public Integer getDayKillometr() {
        return DayKillometr;
    }

    public String getNameAddOil() {
        return NameAddOil;
    }
}
