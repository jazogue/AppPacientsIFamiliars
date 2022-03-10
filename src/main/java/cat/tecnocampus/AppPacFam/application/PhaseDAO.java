package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;

public interface PhaseDAO {
	
	List<PhaseDTO> getPhasesByPatientId(String id);
	
}
