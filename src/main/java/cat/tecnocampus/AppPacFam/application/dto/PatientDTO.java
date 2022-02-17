package cat.tecnocampus.AppPacFam.application.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientDTO {
	
	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email cannot be blank")
	private String idPatient;
	private List<LocationDTO> locations = new ArrayList<>();
	private List<PhaseDTO> phases = new ArrayList<>();
	private List<StateDTO> states = new ArrayList<>();
	
	public PatientDTO() {
		
	}
	
	public String getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public List<LocationDTO> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationDTO> locations) {
		this.locations = locations;
	}
	public List<PhaseDTO> getPhases() {
		return phases;
	}
	public void setPhases(List<PhaseDTO> phases) {
		this.phases = phases;
	}
	public List<StateDTO> getStates() {
		return states;
	}
	public void setStates(List<StateDTO> states) {
		this.states = states;
	}
	
	
}
