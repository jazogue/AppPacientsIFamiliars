package cat.tecnocampus.AppPacFam.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class State {
	private String idState;
	private String stateName;
	private Date entryTime;
	
	public State() {
		
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	
}
