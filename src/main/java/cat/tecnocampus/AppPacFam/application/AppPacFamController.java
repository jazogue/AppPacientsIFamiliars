package cat.tecnocampus.AppPacFam.application;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.domain.Location;
import cat.tecnocampus.AppPacFam.domain.Patient;
import cat.tecnocampus.AppPacFam.domain.Phase;
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
	private LocationDAO locationDAO;
	private PhaseDAO phaseDAO;
	private StateDAO stateDAO;

	public AppPacFamController(PatientDAO patientDAO, LocationDAO locationDAO, PhaseDAO phaseDAO, StateDAO stateDAO) {
		this.patientDAO = patientDAO;
		this.locationDAO = locationDAO;
		this.phaseDAO = phaseDAO;
		this.stateDAO = stateDAO;
	}

	public List<PatientDTO> getPatients() {
		return patientDAO.getPatients();
	}

	public PatientDTO getPatientById(String id) {
		return patientDAO.getPatientById(id);
	}

	public List<LocationDTO> getLocations() {
		return locationDAO.getLocations();
	}

	public List<LocationDTO> getLocationsByPatientId(String id) {
		return locationDAO.getLocationsByPatientId(id);
	}

	public List<PhaseDTO> getPhases() {
		return phaseDAO.getPhases();
	}

	public List<PhaseDTO> getPhasesByPatientId(String id) {
		return phaseDAO.getPhasesByPatientId(id);
	}

	public List<StateDTO> getStatesByPhaseId(String id) {
		return stateDAO.getStatesByPhaseId(id);
	}

	public PatientDTO getPatientSummaryById(String id) {
		PatientDTO patientDto = new PatientDTO();
		patientDto = patientDAO.getPatientById(id);
		patientDto.setLocations(locationDAO.getLocationsByPatientId(id));
		patientDto.setPhases(phaseDAO.getPhasesByPatientId(id));
		patientDto.setPhases(initializeStatesOfPhases(patientDto.getPhases()));

		return patientDto;
	}

	public int getManyNewPatients() {
		return patientDAO.getManyNewPatients();
	}

	public List<PatientDTO> getNewPatients() {
		return patientDAO.getNewPatients();
	}

	public List<LocationDTO> getNewLocationsByPatientId(String id) {
		return locationDAO.getNewLocationsByPatientId(id);
	}

	public int getManyNewLocationsByPatientId(String id) {
		return locationDAO.getManyNewLocationsByPatientId(id);
	}

	public int getManyNewPhasesByPatientId(String id) {
		return phaseDAO.getManyNewPhasesByPatientId(id);
	}

	public List<PhaseDTO> getNewPhasesByPatientId(String id) {
		return phaseDAO.getNewPhasesByPatientId(id);
	}

	public List<StateDTO> getStates() {
		return stateDAO.getStates();
	}

	public List<StateDTO> getStatesByPatientId(String id) {
		return stateDAO.getStatesByPatientId(id);
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

	public void setNewPhase(@Valid PhaseDTO phase, String patientId) {
		phaseDAO.setNewPhase(phase, patientId);
	}

	public void setNewLocation(@Valid LocationDTO location, String patientId) {
		locationDAO.setNewLocation(location, patientId);
	}
	
	public void setNewState(StateDTO state, String phaseId) {
		stateDAO.setNewState(state, phaseId);
	}

	// ******************

	private PatientDTO patientToPatientDTO(Patient patient) {
		PatientDTO result = new PatientDTO();
		result.setPatientId(patient.getPatientId());
		result.setPatientName(patient.getPatientName());
		result.setFirstSurname(patient.getFirstSurname());
		result.setSecondSurname(patient.getSecondSurname());
		result.setLocations(
				patient.getLocations().stream().map(this::LocationToLocationDTO).collect(Collectors.toList()));
		result.setPhases(patient.getPhases().stream().map(this::PhaseToPhaseDTO).collect(Collectors.toList()));

		return result;
	}

	private LocationDTO LocationToLocationDTO(Location location) {
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setLocationId(location.getLocationId());
		locationDTO.setLocationName(location.getLocationName());
		locationDTO.setEntryTime(location.getEntryTime());
		locationDTO.setDepartureTime(location.getDepartureTime());

		return locationDTO;
	}

	private StateDTO StateToStateDTO(State state) {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setStateId(state.getStateId());
		stateDTO.setStateName(state.getStateName());
		stateDTO.setStartTime(state.getStartTime());

		return stateDTO;
	}

	private PhaseDTO PhaseToPhaseDTO(Phase phase) {
		PhaseDTO phaseDTO = new PhaseDTO();
		phaseDTO.setPhaseId(phase.getIdPhase());
		phaseDTO.setPhaseName(phase.getPhaseName());
		phaseDTO.setStartTime(phase.getStartTime());
		phaseDTO.setFinishedTime(phase.getFinishedTime());
		phaseDTO.setStates(phase.getStates().stream().map(this::StateToStateDTO).collect(Collectors.toList()));

		return phaseDTO;
	}

	private Patient patientDTOtoPatient(PatientDTO patientDTO) {
		Patient result = new Patient();
		result.setPatientId(patientDTO.getPatientId());
		result.setPatientName(patientDTO.getPatientName());
		result.setFirstSurname(patientDTO.getFirstSurname());
		result.setSecondSurname(patientDTO.getSecondSurname());
		result.setLocations(
				patientDTO.getLocations().stream().map(this::LocationDTOtoLocation).collect(Collectors.toList()));
		result.setPhases(patientDTO.getPhases().stream().map(this::PhaseDTOtoPhase).collect(Collectors.toList()));

		return result;
	}

	private Location LocationDTOtoLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setLocationId(locationDTO.getLocationId());
		location.setLocationName(locationDTO.getLocationName());
		location.setEntryTime(locationDTO.getEntryTime());
		location.setDepartureTime(locationDTO.getDepartureTime());

		return location;
	}

	private Phase PhaseDTOtoPhase(PhaseDTO phaseDTO) {
		Phase phase = new Phase();
		phase.setIdPhase(phaseDTO.getPhaseId());
		phase.setPhaseName(phaseDTO.getPhaseName());
		phase.setStartTime(phaseDTO.getStartTime());
		phase.setFinishedTime(phaseDTO.getFinishedTime());
		phase.setStates(phaseDTO.getStates().stream().map(this::StateDTOtoState).collect(Collectors.toList()));

		return phase;
	}

	private State StateDTOtoState(StateDTO stateDTO) {
		State state = new State();
		state.setStateId(stateDTO.getStateId());
		state.setStateName(stateDTO.getStateName());
		state.setStartTime(stateDTO.getStartTime());

		return state;
	}

	private List<PhaseDTO> initializeStatesOfPhases(List<PhaseDTO> phases) {
		for (int i = 0; i < phases.size(); i++) {
			phases.get(i).setStates(stateDAO.getStatesByPhaseId(phases.get(i).getPhaseId()));
		}
		return phases;
	}

	

}
