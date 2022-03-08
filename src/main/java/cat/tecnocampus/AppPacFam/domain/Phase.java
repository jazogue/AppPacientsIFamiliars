package cat.tecnocampus.AppPacFam.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Phase {
	private String idPhase;
	private String name;
	private LocalDateTime entryTime;
	private LocalDateTime departureTime;
	private List<State> states = new ArrayList<>();
	
	public Phase() {
		
	}

	public String getIdPhase() {
		return idPhase;
	}

	public void setIdPhase(String idPhase) {
		this.idPhase = idPhase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
}
