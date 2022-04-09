package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StateDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String stateId;
	private String stateName;
	private Date startTime;

	public StateDTO() {

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
