package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PhaseDTO {
	@NotNull(message = "Email cannot be null")
	@NotBlank(message = "Email cannot be blank")
	private String idPhase;
	private String name;
	private LocalDateTime entryTime;
	private LocalDateTime departureTime;
	
	public PhaseDTO() {
		
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
	
	
}
