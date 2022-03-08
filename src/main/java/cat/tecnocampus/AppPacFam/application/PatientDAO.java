package cat.tecnocampus.AppPacFam.application;


import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface PatientDAO {
    
	PatientDTO getPatientById(String id);
	
	List<LocationDTO> getLocationsByPatientId(String id);
	
	List<PhaseDTO> getPhasesByPatientId(String id);
	
	List<StateDTO> getStatesByPatientId(String id);

	List<StateDTO> getStatesByPhasesId(String id);
}
