package cat.tecnocampus.AppPacFam.application;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.domain.Location;
import cat.tecnocampus.AppPacFam.domain.Patient;
import cat.tecnocampus.AppPacFam.domain.Phase;
import cat.tecnocampus.AppPacFam.domain.State;
import cat.tecnocampus.tinder.application.dto.ProfileDTO;
import cat.tecnocampus.tinder.domain.Profile;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service //same as @Component
public class AppPacFamController {
	private PatientDAO patientDAO;

	public AppPacFamController(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	public PatientDTO getPatient(String id) {
		return patientDAO.getPatientById(id);
	}


	public List<LocationDTO> getLocations(String id) {
		PatientDTO patientDTO = this.getPatient(id);
		return getLocationsDTOS(patientDTO);
	}


	//******************
	
	
	private PatientDTO patientToPatientDTO(Patient patient) {
		PatientDTO result = new PatientDTO();
		result.setIdPatient(patient.getIdPatient());
		result.setLocations(patient.getLocations().stream().map(this::LocationToLocationDTO).collect(Collectors.toList()));
		result.setPhases(patient.getPhases().stream().map(this::PhaseToPhaseDTO).collect(Collectors.toList()));
		result.setStates(patient.getStates().stream().map(this::StateToStateDTO).collect(Collectors.toList()));

		return result;
	}
	
	
	private LocationDTO LocationToLocationDTO(Location location) {
		LocationDTO locationDTO = new LocationDTO();
		locationDTO.setIdLocation(location.getIdLocation());
		locationDTO.setName(location.getName());
		locationDTO.setEntryTime(location.getEntryTime());
		locationDTO.setDepartureTime(location.getDepartureTime());

		return locationDTO;
	}
	
	private StateDTO StateToStateDTO(State state) {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setIdState(state.getIdState());
		stateDTO.setName(state.getName());
		stateDTO.setEntryTime(state.getEntryTime());
		stateDTO.setDepartureTime(state.getDepartureTime());

		return stateDTO;
	}
	
	private PhaseDTO PhaseToPhaseDTO(Phase phase) {
		PhaseDTO phaseDTO = new PhaseDTO();
		phaseDTO.setIdPhase(phase.getIdPhase());
		phaseDTO.setName(phase.getName());
		phaseDTO.setEntryTime(phase.getEntryTime());
		phaseDTO.setDepartureTime(phase.getDepartureTime());

		return phaseDTO;
	}
	
	private Patient patientDTOtoPatient(PatientDTO patientDTO) {
		Patient result = new Patient();
		result.setIdPatient(patientDTO.getIdPatient());
		result.setLocations(patientDTO.getLocations().stream().map(this::LocationDTOtoLocation).collect(Collectors.toList()));
		result.setPhases(patientDTO.getPhases().stream().map(this::PhaseDTOtoPhase).collect(Collectors.toList()));
		result.setStates(patientDTO.getStates().stream().map(this::StateDTOtoState).collect(Collectors.toList()));

		return result;
	}
	
	
	private Location LocationDTOtoLocation(LocationDTO locationDTO) {
		Location location = new Location();
		location.setIdLocation(locationDTO.getIdLocation());
		location.setName(locationDTO.getName());
		location.setEntryTime(locationDTO.getEntryTime());
		location.setDepartureTime(locationDTO.getDepartureTime());

		return location;
	}
	
	private Phase PhaseDTOtoPhase(PhaseDTO phaseDTO) {
		Phase phase = new Phase();
		phase.setIdPhase(phaseDTO.getIdPhase());
		phase.setName(phaseDTO.getName());
		phase.setEntryTime(phaseDTO.getEntryTime());
		phase.setDepartureTime(phaseDTO.getDepartureTime());

		return phase;
	}
	
	
	private State StateDTOtoState(StateDTO stateDTO) {
		State state = new State();
		state.setIdState(stateDTO.getIdState());
		state.setName(stateDTO.getName());
		state.setEntryTime(stateDTO.getEntryTime());
		state.setDepartureTime(stateDTO.getDepartureTime());

		return state;
	}
	
	
	
	

	
	

}
