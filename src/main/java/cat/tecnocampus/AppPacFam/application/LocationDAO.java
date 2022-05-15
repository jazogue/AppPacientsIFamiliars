package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;

public interface LocationDAO {

	List<LocationDTO> getAllLocations();

	void setNewLocation(LocationDTO location);

	LocationDTO getLocationById(String locationId);

	List<LocationDTO> getLocationsByPatientId(String patientId);
	
}
