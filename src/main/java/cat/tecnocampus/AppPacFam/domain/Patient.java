package cat.tecnocampus.AppPacFam.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Patient {
	public enum HospitalCareType {quirurgic, urgencies}
	
	private String patientId;
	private String patientName;
	private String firstSurname;
	private String secondSurname;
	private String healthCardIdentifier;
	private HospitalCareType hospitalCareType;
	private List<State> states = new ArrayList<>();

	public Patient() {
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

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> list) {
		this.states = list;
	}

	public HospitalCareType getHospitalCareType() {
		return hospitalCareType;
	}

	public void setHospitalCareType(HospitalCareType hospitalCareType) {
		this.hospitalCareType = hospitalCareType;
	}

	public String getHealthCardIdentifier() {
		return healthCardIdentifier;
	}

	public void setHealthCardIdentifier(String healthCardIdentifier) {
		this.healthCardIdentifier = healthCardIdentifier;
	}

}
