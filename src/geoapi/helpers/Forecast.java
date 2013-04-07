package geoapi.helpers;

public class Forecast {

	long time;
	double latitude;
	double longitude;
	double temperature;
	double cloudCover;
	double humidity;
	double precipIntensity;
	String precipType;
	long sunriseTime;
	long sunsetTime;
	double windSpeed;
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(double cloudCover) {
		this.cloudCover = cloudCover;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getPrecipIntensity() {
		return precipIntensity;
	}
	public void setPrecipIntensity(double precipIntensity) {
		this.precipIntensity = precipIntensity;
	}
	public String getPrecipType() {
		return precipType;
	}
	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}
	public long getSunriseTime() {
		return sunriseTime;
	}
	public void setSunriseTime(long sunriseTime) {
		this.sunriseTime = sunriseTime;
	}
	public long getSunsetTime() {
		return sunsetTime;
	}
	public void setSunsetTime(long sunsetTime) {
		this.sunsetTime = sunsetTime;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
}
