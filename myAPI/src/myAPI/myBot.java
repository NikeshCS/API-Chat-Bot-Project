package myAPI;
/*
 * myBot - Contains two bots that perform various actions.
 * myWeather bot is a weather bot which prompts the user to input an action that it can perform.
 * myNews bot is a news bot which prompts the user to input an action that it can perform.
 * @Author: Nikesh Patel
 */

public class myBot 
{

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		myWeather bot = new myWeather();
		myNews bot2 = new myNews();
		
		//WeatherBot
		//Enable debugging output
		bot.setVerbose(true);
		
		//Connect to the IRC server.
        bot.connect("irc.freenode.net");

        //Join the #irchacks channel.
        bot.joinChannel("#irchacks");
        
        //Display messages
        bot.sendMessage("#irchacks","Hello! I am a weather bot - type temperature [city] to get the temperature of a specific city. (EX: temperature plano)");
        bot.sendMessage("#irchacks","                          - type humidity [city] to get the humidity of a specific city (EX: humidity plano)");
        bot.sendMessage("#irchacks","                          - type windgust [city] to get the windgust of a specific city (EX: windgust plano)");
        
        //NewsBot
        //Enable debugging output
        bot2.setVerbose(true);
        	
        // Connect to the IRC server.
        bot2.connect("irc.freenode.net");
                
        //Display Messages
        bot2.joinChannel("#irchacks");
        bot2.sendMessage("#irchacks","Hello! I am a news bot - type news (to recieve the random latest news article).");
        bot2.sendMessage("#irchacks","I retrieve information such as the title, author, description, and the url of the random article.");
   
	
	}
}


