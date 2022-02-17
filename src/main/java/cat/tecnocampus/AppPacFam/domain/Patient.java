package cat.tecnocampus.AppPacFam.domain;

import java.util.ArrayList;
import java.util.List;

public class Patient {
	private String idPatient;
	private List<Location> locations = new ArrayList<>();
	private List<Phase> phases = new ArrayList<>();
	private List<State> states = new ArrayList<>();
	
	public Patient() {
		
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
	
}
