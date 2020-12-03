package com.company;

import java.util.Comparator;

public class LondonWeather {

    private String weatherStateName;
    private String created;
    private double minTemp;
    private double maxTemp;
    private double theTemp;
    private double windSpeed;
    private double airPressure;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LondonWeather() {
    }

    /**
     * 
     * @param weatherStateName
     * @param theTemp
     * @param created
     * @param airPressure
     * @param maxTemp
     * @param windSpeed
     * @param minTemp
     */
    public LondonWeather(String weatherStateName, String created, double minTemp, double maxTemp, double theTemp, double windSpeed, double airPressure) {
        super();
        this.weatherStateName = weatherStateName;
        this.created = created;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.airPressure = airPressure;
    }


    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public void setTheTemp(double theTemp) {
        this.theTemp = theTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }


    @Override
    public String toString() {
        return "Weather: " + weatherStateName + ", " +
                "date and time: " + created + ", " +
                "minimal temperature: " + minTemp + ", " +
                "maximal temperature: " + maxTemp + ", " +
                "current temperature: " + theTemp + ", " +
                "wind speed: " + windSpeed + ", " +
                "air pressure: " + airPressure + ";" + System.lineSeparator();
    }

    public static Comparator<LondonWeather> byNameWeatherAsc = Comparator.comparing(o -> o.weatherStateName);
    public static Comparator<LondonWeather> byNameWeatherDesc = (o1, o2) -> o2.weatherStateName.compareTo(o1.weatherStateName);
    public static Comparator<LondonWeather> byTheTempAsc = ((o1, o2) -> o1.theTemp > o2.theTemp ? 1 : o1.theTemp < o2.theTemp ? -1 : 0);
    public static Comparator<LondonWeather> byTheTempDesc = ((o1, o2) -> o1.theTemp < o2.theTemp ? 1 : o1.theTemp > o2.theTemp ? -1 : 0);

}
