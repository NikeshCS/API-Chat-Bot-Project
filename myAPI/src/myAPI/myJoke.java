package myAPI;

/*
 * News Bot - Retrieves information on a random latest article from the newsapi.org
 * Make web requests to receive data from open weather map and create an interfaceable chat bot. 
 * @Author: Nikesh Patel
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.jibble.pircbot.PircBot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class myJoke 
{
	public static void main(String[] args)
	{
		startWebRequest();
	}
	/*
	//Bot messages
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		//if the user message if news it will execute
		if(message.equalsIgnoreCase("joke"))
		{
			sendMessage(channel,"Looking for a joke.....");
			sendMessage(channel,"Picking a random joke.....");
			sendMessage(channel,"Successful! Information on the random joke: " + startWebRequest());
		}
	}
	*/
	
	//Makes web request and returns the random article.
	//@return article is the parse information retrieved from the newsapi.
	static String startWebRequest()
	{
		String jokeURL = "https://api.chucknorris.io/jokes/random";
		StringBuilder result = new StringBuilder(); //will hold the java String after converting from JSON 
		try 
		{	
			URL url = new URL(jokeURL); //the url we want to parse
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Used to make a single connection to that URL
			conn.setRequestMethod("GET"); //The Type of request we want to make
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); //pass in the instance of HttpURLConnection 
			String line;
		    	while ((line = rd.readLine()) != null) 
		    	{ //turn BufferedReader value into type String
		    		result.append(line);
		    	}
		    	System.out.println(result.toString());
		    rd.close();
		    //what do we want to search the API for?
		    
		}
		
		catch(Exception e){return "Error! Exception: " + e;}
		return result.toString();
	}
	
	//Parses the latest news, stores Json objects into arrays and parses each individual element.
	//@return output that will be generated in freenode
	static String parseJoke(String json) 
	{
		
		return json;
	}

}
