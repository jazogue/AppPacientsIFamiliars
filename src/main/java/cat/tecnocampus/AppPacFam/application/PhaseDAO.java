package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;

public interface PhaseDAO {
	
	List<PhaseDTO> getPhasesByPatientId(String id);

	List<PhaseDTO> getNewPhasesByPatientId(String id);

	int getManyNewPhasesByPatientId(String id);

	List<PhaseDTO> getPhases();

	void setNewPhase(@Valid PhaseDTO phase, String patientId);
	
}
