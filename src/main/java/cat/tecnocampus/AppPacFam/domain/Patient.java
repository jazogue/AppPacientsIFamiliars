package cat.tecnocampus.AppPacFam.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
	private String patientId;
	private String patientName;
	private String firstSurname;
	private String secondSurname;
	private List<Location> locations = new ArrayList<>();
	private List<Phase> phases = new ArrayList<>();

	public Patient() {
		this.patientId = UUID.randomUUID().toString();
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
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

	@Override
	public String toString() {
		return "Patient{" + "patientId='" + patientId + '\'' + ", patientName=" + patientName + ", firstSurname='"
				+ firstSurname + '\'' + ", secondSurname=" + secondSurname + '}';
	}

}
