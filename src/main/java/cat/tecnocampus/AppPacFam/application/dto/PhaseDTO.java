package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PhaseDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String phaseId;
	private String name;
	private LocalDateTime entryTime;
	private LocalDateTime departureTime;
	private List<StateDTO> states = new ArrayList<>();
	
	public PhaseDTO() {
		
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
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
	public List<StateDTO> getStates() {
		return states;
	}
	public void setStates(List<StateDTO> states) {
		this.states = states;
	}
	
}
