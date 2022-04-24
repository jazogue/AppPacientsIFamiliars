package cat.tecnocampus.AppPacFam.domain;

import java.util.Date;
import java.util.UUID;

public class State {
	private String stateId;
	private String stateName;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
