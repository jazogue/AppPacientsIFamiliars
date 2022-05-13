package cat.tecnocampus.AppPacFam.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
	private String patientId;
	private String patientName;
	private String firstSurname;
	private String secondSurname;
	private String healthCardIdentifier;
	private List<Admission> admissions = new ArrayList<>();

	public Patient() {
		this.patientId = UUID.randomUUID().toString().substring(0, 5);
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

	public String getHealthCardIdentifier() {
		return healthCardIdentifier;
	}

	public void setHealthCardIdentifier(String healthCardIdentifier) {
		this.healthCardIdentifier = healthCardIdentifier;
	}

	public List<Admission> getAdmissions() {
		return admissions;
	}

	public void setAdmissions(List<Admission> admissions) {
		this.admissions = admissions;
	}

}
