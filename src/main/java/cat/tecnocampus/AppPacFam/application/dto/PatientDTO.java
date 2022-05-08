package cat.tecnocampus.AppPacFam.application.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cat.tecnocampus.AppPacFam.domain.Patient.HospitalCareType;

public class PatientDTO {
	
	@NotNull(message = "Id cannot be null")
	@NotBlank(message = "Id cannot be blank")
	private String patientId;
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be blank")
	private String patientName;
	@NotNull(message = "First surname cannot be null")
	@NotBlank(message = "First surname cannot be blank")
	private String firstSurname;
	@NotNull(message = "Second surname cannot be null")
	@NotBlank(message = "Second surname cannot be blank")
	private String secondSurname;
	private HospitalCareType hospitalCareType;
	@NotNull(message = "Health card identifier cannot be null")
	@NotBlank(message = "Health card identifier cannot be blank")
	private String healthCardIdentifier;
	private List<StateDTO> states = new ArrayList<>();


	public PatientDTO() {
		this.patientId = UUID.randomUUID().toString().substring(0,5);
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getHealthCardIdentifier() {
		return healthCardIdentifier;
	}

	public void setHealthCardIdentifier(String healthCardIdentifier) {
		this.healthCardIdentifier = healthCardIdentifier;
	}
	
	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
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
