package geoapi.invokations;

import geoapi.helpers.Forecast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ForecastIO {

	
	private final String accessToken = "8784205dc7f9893559a3fa15f953e847";
	private final String serverURL = "https://api.forecast.io/forecast/" + accessToken;
	
	public Forecast invokeForecastIO(double latitude, double longitude,
			long epoch) {

//		SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
//		
//		Date date = null;
//		try {
//			date = df.parse(dateTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		long epoch = date.getTime();
		String params = latitude + "," + longitude + ", " + epoch;

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		ClientResponse response = client.resource(serverURL).path(params)
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		

		JSONObject json = (JSONObject) JSONSerializer.toJSON(response);    
		Forecast forecast = new Forecast();
		
		forecast.setLatitude(json.getDouble("latitude"));
		forecast.setLongitude(json.getDouble("longitude"));
		
		JSONObject daily = json.getJSONObject("daily").getJSONArray("data").getJSONObject(0);
		forecast.setTime(daily.getLong("time"));
		forecast.setTemperature((daily.getDouble("temperatureMax") + daily.getDouble("temperatureMin")) / 2);
		forecast.setCloudCover(daily.getDouble("cloudCover"));
		forecast.setHumidity(daily.getDouble("humidity"));
		forecast.setPrecipIntensity(daily.getDouble("precipIntensity"));
		forecast.setPrecipType(daily.getString("precipType"));
		
		forecast.setSunriseTime(daily.getLong("sunriseTime"));
		forecast.setSunsetTime(daily.getLong("sunsetTime"));
		forecast.setWindSpeed(daily.getDouble("windSpeed"));
	
		return forecast;
	}
}
