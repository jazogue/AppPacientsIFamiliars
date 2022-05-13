package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface StateDAO {

	List<StateDTO> getStates();

	List<StateDTO> getStatesByAdmissionId(String id);

	void setNewGenericState(StateDTO state);

	List<StateDTO> getTypedStatesByPatientId(String id, boolean type, String idiom);

	void setNewGenericStateToPatient(String stateId, String admissionId);

	void setNewCustomStateToPatient(StateDTO state, String admissionId);

	List<StateDTO> getTypedStates(boolean type, String idiom);

}
