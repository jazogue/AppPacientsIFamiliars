package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface LocationDAO {

	List<LocationDTO> getLocationsByPatientId(String id);

	List<LocationDTO> getNewLocationsByPatientId(String id);

	int getManyNewLocationsByPatientId(String id);

	List<LocationDTO> getLocations();

}
