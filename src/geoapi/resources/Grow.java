package geoapi.resources;

import geoapi.helpers.Forecast;
import geoapi.helpers.Request;
import geoapi.invokations.ForecastIO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/Grow")
public class Grow {

	@GET
	@Path("getMetric")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public double getMetric(Request request){
		
		double percentage = 2.0;
		
		//TODO: Invoke ForecastIO API
		
		ForecastIO fIO = new ForecastIO();
		Forecast forecast = fIO.invokeForecastIO(request.getLatitude(), request.getLongitude(), request.getEpoch());
		
		System.out.println(forecast.getLatitude());
		
		//TODO: Invoke Soil API
		
		//TODO: Invoke Percentage Method Calculation
		
		return percentage;
	}
	
	
}
