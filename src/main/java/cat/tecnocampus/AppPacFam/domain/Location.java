package cat.tecnocampus.AppPacFam.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Location {
	private String locationId;
	private String LocationName;
	private Date entryTime;
	private Date departureTime;

	public Location() {
		this.locationId = UUID.randomUUID().toString();
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String LocationName) {
		this.LocationName = LocationName;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

}
