package cat.tecnocampus.AppPacFam.application.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String locationId;
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be blank")
	private String locationName;
	
	public LocationDTO() {
		this.setLocationId(UUID.randomUUID().toString());
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
}
