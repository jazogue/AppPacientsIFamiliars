package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import com.google.gson.JsonObject;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

public interface StateDAO {

	List<StateDTO> getStatesByAdmissionId(String id, String idiom);

	JsonObject setNewGenericState(StateDTO state);

	void setNewGenericStateToPatient(String stateId, String admissionId);

	void setNewCustomStateToPatient(StateDTO state, String admissionId);

	List<StateDTO> getTypedStates(boolean type, String idiom);

}
