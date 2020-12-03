package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<LondonWeather> WeatherList = new ArrayList<LondonWeather>();

    public static void main(String[] args) {
        int year = 0;
        int month = 0;
        int day = 0;

        System.out.println("Please, enter the day for which you want to see the weather in London.");
        System.out.println("Enter the year not earlier than 2013: ");
        try {
            year = readInteger(2012, 2021);
            System.out.println("Enter month: ");
            month = readInteger(0, 13);
            System.out.println("Enter the day: ");
            day = readInteger(0, 31);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://www.metaweather.com/api/location/44418/" + year + "/" + month + "/" + day;
        jsonGetter.run();

        System.out.println("Waiting for data...");
        String jsonString = jsonGetter.jsonIn;
        System.out.println(jsonString);

        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        System.out.println();

        JSONArray jsonArray = (JSONArray) obj;
        System.out.println(jsonArray.toJSONString());
        System.out.println();

        for(Object jsonObject : jsonArray){
            JSONObject current = (JSONObject) jsonObject;
            String weatherStateName = (String) current.get("weather_state_name");
            String created = (String) current.get("created");
            double minTemp = (double) current.get("min_temp");
            double maxTemp = (double) current.get("max_temp");
            double theTemp = (double) current.get("the_temp");
            double windSpeed = (double) current.get("wind_speed");
            double airPressure = (double) current.get("air_pressure");
            LondonWeather lnWeather = new LondonWeather(weatherStateName, created, minTemp, maxTemp,
                                                        theTemp, windSpeed, airPressure);
            WeatherList.add(lnWeather);
        }

        System.out.println("Imported data after parsing:\n" + WeatherList);
        System.out.println();

        WeatherList.sort(LondonWeather.byNameWeatherAsc);
        System.out.println("After sorting by name ascending:\n" + WeatherList);
        System.lineSeparator();
        WeatherList.sort(LondonWeather.byNameWeatherDesc);
        System.out.println("After sorting by name descending:\n" + WeatherList);
        System.lineSeparator();

        WeatherList.sort(LondonWeather.byTheTempAsc);
        System.out.println("After sorting by current temperature ascending:\n" + WeatherList);
        System.lineSeparator();
        WeatherList.sort(LondonWeather.byTheTempDesc);
        System.out.println("After sorting by current temperature descending:\n" + WeatherList);
        System.lineSeparator();


        ArrayList<LondonWeather> filtered = filterByWeather("Heavy Rain");
        System.out.println("Filtered by Heavy Rain weather:" + filtered);
    }

    private static ArrayList<LondonWeather> filterByWeather(String weatherName){
        ArrayList<LondonWeather> result = new ArrayList<LondonWeather>();
        for (LondonWeather lnw : WeatherList){
            if (lnw.getWeatherStateName().toLowerCase().equals(weatherName.toLowerCase()))
                result.add(lnw);
        }

        return result;
    }

    public static int readInteger(int min, int max) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                int result = Integer.parseInt(input.next());
                if (result > min && result < max) return result;
                else {
                    System.out.println("Value must be > " + min + " and < " + max);
                }
            } catch (Exception e) {
                System.out.println("Enter a number, please");
            }
        }
    }
}
