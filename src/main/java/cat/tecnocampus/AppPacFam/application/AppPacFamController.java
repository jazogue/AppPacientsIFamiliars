package cat.tecnocampus.AppPacFam.application;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.domain.Location;
import cat.tecnocampus.AppPacFam.domain.Patient;
import cat.tecnocampus.AppPacFam.domain.State;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service // same as @Component
public class AppPacFamController {
	private PatientDAO patientDAO;
	private StateDAO stateDAO;

	public AppPacFamController(PatientDAO patientDAO, StateDAO stateDAO) {
		this.patientDAO = patientDAO;
		this.stateDAO = stateDAO;
	}

	public List<PatientDTO> getPatients() {
		return patientDAO.getPatients();
	}

	public PatientDTO getPatientById(String id) {
		return patientDAO.getPatientById(id);
	}


	public List<StateDTO> getStates() {
		return stateDAO.getStates();
	}

	public List<StateDTO> getStatesByPatientId(String id) {
		return stateDAO.getStatesByPatientId(id);
	}



	public PatientDTO getPatientSummaryById(String id) {
		PatientDTO patientDto = new PatientDTO();
		patientDto = patientDAO.getPatientById(id);
		patientDto.setStates(stateDAO.getStatesByPatientId(id));

		return patientDto;
	}

	public int getManyNewPatients() {
		return patientDAO.getManyNewPatients();
	}

	public List<PatientDTO> getNewPatients() {
		return patientDAO.getNewPatients();
	}

	public int getManyNewStatesByPatientId(String id) {
		return stateDAO.getManyNewStatesByPatientId(id);
	}

	public List<StateDTO> getNewStatesByPatientId(String id) {
		return stateDAO.getNewStatesByPatientId(id);
	}

	public void setNewPatient(PatientDTO patient) {
		patientDAO.setNewPatient(patient);

	}

	public void setNewGenericState(StateDTO state) {
		stateDAO.setNewGenericState(state);
	}
	
	public List<StateDTO>  getTypedStatesByPatientId(String id, boolean type) {
		return stateDAO.getTypedStatesByPatientId(id, type);
	}
	
	public void setNewGenericStateToPatient(String stateId, String patientId) {
		 stateDAO.setNewGenericStateToPatient(stateId, patientId);
	}
	
	public void setNewCustomStateToPatient(StateDTO state, String patientId) {
		 stateDAO.setNewCustomStateToPatient(state, patientId);
	}


	// ******************

	private PatientDTO patientToPatientDTO(Patient patient) {
		PatientDTO result = new PatientDTO();
		result.setPatientId(patient.getPatientId());
		result.setPatientName(patient.getPatientName());
		result.setFirstSurname(patient.getFirstSurname());
		result.setSecondSurname(patient.getSecondSurname());
		result.setHospitalCareType(patient.getHospitalCareType());
		result.setStates(
				patient.getStates().stream().map(this::StateToStateDTO).collect(Collectors.toList()));
	
		return result;
	}


	private StateDTO StateToStateDTO(State state) {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setStateId(state.getStateId());
		stateDTO.setStateName(state.getStateName());
		stateDTO.setStartTime(state.getStartTime());
		stateDTO.setStateType(state.getStateType());

		return stateDTO;
	}


	private Patient patientDTOtoPatient(PatientDTO patientDTO) {
		Patient result = new Patient();
		result.setPatientId(patientDTO.getPatientId());
		result.setPatientName(patientDTO.getPatientName());
		result.setFirstSurname(patientDTO.getFirstSurname());
		result.setSecondSurname(patientDTO.getSecondSurname());
		result.setHospitalCareType(patientDTO.getHospitalCareType());
		result.setStates(
				patientDTO.getStates().stream().map(this::StateDTOtoState).collect(Collectors.toList()));

		return result;
	}


	private State StateDTOtoState(StateDTO stateDTO) {
		State state = new State();
		state.setStateId(stateDTO.getStateId());
		state.setStateName(stateDTO.getStateName());
		state.setStartTime(stateDTO.getStartTime());
		state.setStateType(stateDTO.getStateType());

		return state;
	}


	


	

}
