package cat.tecnocampus.AppPacFam.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Admission {

	private String admissionId;
	public enum HospitalCareType {
		quirurgic, urgencies
	}

	private HospitalCareType hospitalCareType;
	private Date startDate;
	private Date finaltDate;
	private List<State> states = new ArrayList<>();

	public String getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(String admissionId) {
		this.admissionId = admissionId;
	}

	public HospitalCareType getHospitalCareType() {
		return hospitalCareType;
	}

	public void setHospitalCareType(HospitalCareType hospitalCareType) {
		this.hospitalCareType = hospitalCareType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinaltDate() {
		return finaltDate;
	}

	public void setFinaltDate(Date finaltDate) {
		this.finaltDate = finaltDate;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
}
