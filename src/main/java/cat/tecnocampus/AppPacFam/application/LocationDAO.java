package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;

public interface LocationDAO {

	List<LocationDTO> getAllLocations();
	
}
