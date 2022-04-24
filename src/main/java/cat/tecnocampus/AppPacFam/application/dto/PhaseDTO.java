package cat.tecnocampus.AppPacFam.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cat.tecnocampus.AppPacFam.domain.Phase.HospitalCareType;

public class PhaseDTO {
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String phaseId;
	@NotNull(message = "Phase name cannot be null")
	@NotBlank(message = "Phase name cannot be blank")
	private String phaseName;
	@NotNull(message = "Start time cannot be null")
	private Date startTime;
	private Date finishedTime;
	@NotNull(message = "Hospital care type cannot be null")
	private HospitalCareType hospitalCareType;
	private List<StateDTO> states = new ArrayList<>();

	public PhaseDTO() {
		this.phaseId = UUID.randomUUID().toString();
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
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

	public List<StateDTO> getStates() {
		return states;
	}

	public void setStates(List<StateDTO> states) {
		this.states = states;
	}

	public HospitalCareType getHospitalCareType() {
		return hospitalCareType;
	}

	public void setHospitalCareType(HospitalCareType hospitalCareType) {
		this.hospitalCareType = hospitalCareType;
	}

}
