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

public class myNews extends PircBot 
{
	//Bot name
	public myNews()
	{
		this.setName("iNews");
	}
	
	//Bot messages
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		//if the user message if news it will execute
		if(message.equalsIgnoreCase("news"))
		{
			sendMessage(channel,"Looking for the latest articles.....");
			sendMessage(channel,"Picking a random article.....");
			sendMessage(channel,"Successful! Information on the random article: " + startWebRequest());
		}
	}
	
	//Makes web request and returns the random article.
	//@return article is the parse information retrieved from the newsapi.
	static String startWebRequest(){
		String weatherURL = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=4d1c1628741041e7bdff4b6d0cc2d6ea";
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
		    System.out.println(result.toString());
		    String article = parseNews(result.toString());
		    System.out.println(article);
		    return article;
		}
		
		catch(Exception e){return "Error! Exception: " + e;}
		
	}
	
	//Parses the latest news, stores Json objects into arrays and parses each individual element.
	//@return output that will be generated in freenode
	static String parseNews(String json) 
	{
		//Generates a number that will be randomly selected from the JsonArray for each indivual element in the news.
		Random rand = new Random();
		
		//Random number assigned to index which will be implemen into JsonArray
		int index = rand.nextInt(8) + 1;
		
		//Creates a Json Object named newsObject
		JsonObject newsObject = new JsonParser().parse(json).getAsJsonObject();
		
		//Parses each attribute from the news Article.
		String description = newsObject.getAsJsonArray("articles").get(index).getAsJsonObject().get("description").getAsString();
		
		
		String author = newsObject.getAsJsonArray("articles").get(index).getAsJsonObject().get("author").getAsString();
		
		
		String newsTitle = newsObject.getAsJsonArray("articles").get(index).getAsJsonObject().get("title").getAsString();
		
		
		String url = newsObject.getAsJsonArray("articles").get(index).getAsJsonObject().get("url").getAsString();
		
		//Returns the output of a randomly selected article and it's individual elements.
		return "Title: " + newsTitle + " " + "Author: " + author +" " + "Description: "+ description + " " + "URL: " + url;
	}

}
