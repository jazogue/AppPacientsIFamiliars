package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StateDTO {
	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email cannot be blank")
	private String idState;
	private String name;
	private LocalDateTime entryTime;
	private LocalDateTime departureTime;
	
	public StateDTO() {
		
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
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
	
	
}
