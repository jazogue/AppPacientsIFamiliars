package cat.tecnocampus.AppPacFam.application.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private List<LocationDTO> locations = new ArrayList<>();
	private List<PhaseDTO> phases = new ArrayList<>();

	public PatientDTO() {
		this.patientId = UUID.randomUUID().toString();
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<LocationDTO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationDTO> locations) {
		this.locations = locations;
	}

	public List<PhaseDTO> getPhases() {
		return phases;
	}

	public void setPhases(List<PhaseDTO> phases) {
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

}
