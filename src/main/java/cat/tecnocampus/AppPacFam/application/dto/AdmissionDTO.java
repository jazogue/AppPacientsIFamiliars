package cat.tecnocampus.AppPacFam.application.dto;

import java.util.Date;
import java.util.UUID;

import cat.tecnocampus.AppPacFam.domain.Admission.HospitalCareType;

public class AdmissionDTO {
	private String admissionId;
	private HospitalCareType hospitalCareType;
	private Date startDate;
	private Date finalDate;

	public AdmissionDTO() {
		this.admissionId = UUID.randomUUID().toString();
	}
	
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

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

}
