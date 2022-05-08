package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;

public interface PatientDAO {

	PatientDTO getPatientById(String id);

	PatientDTO getPatientByAnyCriteria(String value);
	
	int getManyNewPatients();

	List<PatientDTO> getNewPatients();

	List<PatientDTO> getPatients();

	void setNewPatient(PatientDTO patient);

}
