package cat.tecnocampus.AppPacFam.domain;

import java.util.Date;
import java.util.UUID;

public class State {
	public enum StateType {personalitzat, generic}
	
	private String stateId;
	private String eventId;
	private StateType stateType;
	private Date startTime;
	private String locationName;
	private String translatedText;
	
	public State() {
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
	
	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

}
