package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.AdmissionDTO;

public interface AdmissionDAO {
	List<AdmissionDTO> getAdmissionsByPatientId(String patientId);

	AdmissionDTO getActiveAdmissionByPatientId(String patientId);

	void setNewAdmission(AdmissionDTO admission, String patientId);

	void addFinishDate(String admissionId);

	void modifyAdmissionTypeByPatientId(String patientId);
}
