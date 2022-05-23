package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import com.google.gson.JsonObject;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;

public interface LocationDAO {

	List<LocationDTO> getAllLocations();

	JsonObject setNewLocation(LocationDTO location);

	LocationDTO getLocationById(String locationId);

}
