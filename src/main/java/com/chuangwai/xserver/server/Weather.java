package com.chuangwai.xserver.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by xingshi on 15/6/3.
 */
public class Weather {
    private String currentDate = "";
    private String currentCity = "";
    private String pm25 = "";

    public ArrayList<DateWeather> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(ArrayList<DateWeather> weatherData) {
        this.weatherData = weatherData;
    }

    private ArrayList<DateWeather> weatherData = new ArrayList<DateWeather>();
    class DateWeather{
        String date;
        String weather;
        String wind;
        String temperature;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }


    public Weather(String city){
        JSONObject obj = WeatherUtils.getWeatherJson(city);
        currentCity = city;
        currentDate = "2222";
        json2Weather(obj);
    }
    private void json2Weather(JSONObject obj){
        this.currentDate = obj.getString("date");
        JSONArray ret = (JSONArray) obj.get("results");
        JSONObject ret2 = (JSONObject) ret.get(0);
        pm25 = (String) ret2.get("pm25");
        currentCity = (String) ret2.get("currentCity");
        JSONArray weather_data = (JSONArray) ret2.get("weather_data");
        for(int i=0; i<weather_data.size(); i++){
            JSONObject jo = (JSONObject) weather_data.get(i);
            DateWeather dwt = new DateWeather();
            dwt.date = jo.get("date")+"";
            dwt.weather = jo.get("weather")+"";
            dwt.wind = jo.get("wind")+"";
            dwt.temperature = jo.get("temperature")+"";
            weatherData.add(dwt);
        }
    }

    public static void main(String[] args) {
        
        Weather wt = new Weather("北京");
        System.out.println(JSON.toJSONString(wt) + "==========");

        System.out.println(wt.currentCity+"\n"+wt.currentDate+"\n"+wt.pm25+"\n"+wt.weatherData.size());
        for(int i =0;i<wt.weatherData.size();i++){
            System.out.println(wt.weatherData.get(i).date+" "+wt.weatherData.get(i).temperature+" "+wt.weatherData.get(i).weather+" "+wt.weatherData.get(i).wind+" ");

        }
        
    }
}
