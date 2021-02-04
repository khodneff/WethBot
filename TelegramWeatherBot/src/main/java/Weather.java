import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
//b1b15e88fa797225412429c1c50c122a1

public class Weather {

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=459021de2c6d80475cce7df0db921492");
        Scanner in = new Scanner((InputStream) url.getContent());

        String result = "";

        while (in.hasNext()){
            result += in.nextLine();
        }
       // System.out.println("Проверочка");

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for(int i = 0; i < getArray.length(); i++ ) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }



        return "City " + model.getName() + "\n" +
                "Temperature " + model.getTemp() +"C" + "\n" +
                "Humidity " + model.getHumidity() + "\n" +
                "Main " + model.getMain() + "\n" +
                "https://openweathermap.org/img/" + model.getIcon() + ".png";
    }
}
