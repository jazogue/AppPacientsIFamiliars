package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface StateDAO {

	List<StateDTO> getStates();

	List<StateDTO> getStatesByPatientId(String id);

	int getManyNewStatesByPatientId(String id);

	List<StateDTO> getNewStatesByPatientId(String id);

	void setNewGenericState(StateDTO state);

	List<StateDTO> getTypedStatesByPatientId(String id, boolean type);

	void setNewGenericStateToPatient(String stateId, String patientId);

	void setNewCustomStateToPatient(StateDTO state, String patientId);

	List<StateDTO> getTypedStates(boolean type);

}
