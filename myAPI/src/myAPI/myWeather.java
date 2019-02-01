package myAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jibble.pircbot.PircBot;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class myWeather extends PircBot 
{
	//Bot name
	public myWeather() 
	{
        this.setName("iWeather");
    }
	
	//Displays output from bot
	public void onMessage(String channel, String sender,
							String login, String hostname, String message)
	{
		if(message.startsWith("temperature"))
		{
			String city = message.replace("temperature", "");
			sendMessage(channel, "Looking for temperature in" + city + "....");
			sendMessage(channel,"Current temperature in" + city + ": " + startWebRequest(city, 1) + " degrees fahrenheit.");
		}
		else if(message.startsWith("humidity"))
		{
			String city = message.replace("humidity", "");
			sendMessage(channel, "Looking for humidity in" + city + "....");
			sendMessage(channel,"Current Humidity in" + city +": " + startWebRequest(city, 2) + "%");
		}
		else if(message.startsWith("windgust"))
		{
			String city = message.replace("windgust", "");
			sendMessage(channel, "Looking for wind gust in" + city + "....");
			sendMessage(channel,"Current wind gust in" + city + ": " + startWebRequest(city, 3) + " mph.");
		}
		
	}
	
	//Makes web request to open weather and goes through various cases of return.
	//@return goes through numerous cases based on the users input.
	static String startWebRequest(String city, int num){
		String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=d4f074b32c5c58aca1fc412c5885de91";
		StringBuilder result = new StringBuilder(); //will hold the java String after converting from JSON 
		try {	
			URL url = new URL(weatherURL); //the url we want to parse
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Used to make a single connection to that URL
			conn.setRequestMethod("GET"); //The Type of request we want to make
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); //pass in the instance of HttpURLConnection 
			String line;
		    	while ((line = rd.readLine()) != null) 
		    	{ //turn BufferedReader value into type String
		    		result.append(line);
		    	}
		    rd.close();
		    //what do we want to search the API for?
		    String windGust = parseWind(result.toString());
		    System.out.println("Wind gust is currently: " + windGust + " mph");
		    String temperature = parseTemp(result.toString());
		    
		    System.out.println("Temperature is currently: " + temperature + " degrees");
		    String humidity = parseHumidity(result.toString());
		    
		    System.out.println("Humidty is currently: " + humidity + "%");
		    
		    if(num == 1)
		    {
		    	return temperature;
		    }
		    else if (num == 2)
		    {
		    	return humidity;
		    }
		    else if(num == 3)
		    {
		    	return windGust;
		    }
		    else
		    {
		    	return result.toString();
		    }
		}
		catch(Exception e){return "Error! Exception: " + e;}
	}
	
	//Retrieves the gust from the JSON
	//@return gust.toString is the parse wind gust as a string
	static String parseWind(String json) 
	{
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
    
      //Retrieves indivual attributes such as the wind gust from the API.
        JsonObject windObject = MasterWeatherObject.getAsJsonObject("wind"); //type this exactly as it appears in the JSON response
        Double gust = windObject.get("speed").getAsDouble();  //type exactly, type case matters!
        
        return gust.toString();
	}
	
	//Retrieves the temperature from the JSON
	//@return temperature.toString is the parse temperature as a string
	static String parseTemp(String json)
	{
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
    
        //Retrieves indivual attributes such as the temp from the API.
        JsonObject tempObject = MasterWeatherObject.getAsJsonObject("main"); //type this exactly as it appears in the JSON response
        Double temperature = Math.ceil(((tempObject.get("temp").getAsDouble() -273.15)*9/5)+32);  //type exactly, type case matters!
    
        
        return temperature.toString();
	}
	
	//Retrieves the humidity from the JSON
	//@return humidity.toString() is the parse humidity as a string
	static String parseHumidity(String json)
	{
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject  MasterWeatherObject = jelement.getAsJsonObject();
    
        //Retrieves indivual attributes such as the humidity from the API.
        JsonObject humidityObject = MasterWeatherObject.getAsJsonObject("main"); //type this exactly as it appears in the JSON response
        Double humidity = humidityObject.get("humidity").getAsDouble();  //type exactly, type case matters!
        
        return humidity.toString();
	}
 
}
