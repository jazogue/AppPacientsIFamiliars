package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface StateDAO {
	
	List<StateDTO> getStatesByPhaseId(String id);

	List<StateDTO> getStates();

}
