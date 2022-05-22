package cat.tecnocampus.AppPacFam.application.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cat.tecnocampus.AppPacFam.domain.State.StateType;

public class StateDTO {
	@NotNull(message = "State Id cannot be null")
	@NotBlank(message = "State Id cannot be blank")
	private String stateId;
	private String eventId;
	private Date startTime;
	@NotNull(message = "State type cannot be null")
	private StateType stateType;
	private String location;
	private String translatedText;

	public StateDTO() {
		this.stateId = UUID.randomUUID().toString();
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
}
