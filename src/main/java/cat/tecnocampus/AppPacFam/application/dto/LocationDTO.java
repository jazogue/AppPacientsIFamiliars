package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String locationId;
	@NotNull(message = "Location name cannot be null")
	@NotBlank(message = "Location name cannot be blank")
	private String locationName;
	@NotNull(message = "Entry time cannot be null")
	private Date entryTime;
	private Date departureTime;

	public LocationDTO() {
		this.locationId = UUID.randomUUID().toString();
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
