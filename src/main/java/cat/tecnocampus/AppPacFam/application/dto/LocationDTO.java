package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String locationId;
	private String name;
	private LocalDateTime entryTime;
	private LocalDateTime departureTime;
	
	public LocationDTO() {
		
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	
	
}
