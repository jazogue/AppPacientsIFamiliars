package cat.tecnocampus.AppPacFam.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Phase {
	private String idPhase;
	private String phaseName;
	private Date startTime;
	private Date finishedTime;
	private List<State> states = new ArrayList<>();
	
	public Phase() {
		
	}

	public String getIdPhase() {
		return idPhase;
	}

	public void setIdPhase(String idPhase) {
		this.idPhase = idPhase;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
}
