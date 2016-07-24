package util;

import android.text.TextUtils;

import db.CoolWeatherDB;
import model.City;
import model.County;
import model.Province;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Utility {

    //parse and handle the province's data from server
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB,
                                                              String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //save parsed province's data
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return  false;
    }

    //parse and handle the city's data from server
    public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB,
                                                          String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String [] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String p : allCities) {
                    String[] array = p.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceID(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCountyResponse(CoolWeatherDB coolWeatherDB,
                                                            String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String p : allCounties) {
                    String[] array = p.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
