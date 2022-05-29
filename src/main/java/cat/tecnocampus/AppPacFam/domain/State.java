package cat.tecnocampus.AppPacFam.domain;

import java.util.Date;
import java.util.UUID;

public class State {
	public enum StateType {
		personalitzat, generic
	}

	private String stateId;
	private String eventId;
	private StateType stateType;
	private Date startTime;

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

}
