package com.mindtree.restaurantsearchservice.vo;

public class CoOrdinateSearchParams extends SearchParams{
 
	private Double latitude;
	private Double longitude;
	private Float distance;
	
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	
	
	
	
}
