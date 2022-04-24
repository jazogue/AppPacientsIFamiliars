package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface StateDAO {

	List<StateDTO> getStates();

	List<StateDTO> getStatesByPatientId(String id);

	int getManyNewStatesByPatientId(String id);

	List<StateDTO> getNewStatesByPatientId(String id);

	void setNewState(StateDTO state, String patientId);

}
