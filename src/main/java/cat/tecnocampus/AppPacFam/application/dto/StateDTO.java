package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StateDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String stateId;
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String stateName;
	@NotNull(message = "Start time cannot be null")
	private Date startTime;

	public StateDTO() {
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
