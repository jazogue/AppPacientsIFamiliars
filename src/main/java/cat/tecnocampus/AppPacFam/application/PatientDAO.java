package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;

public interface PatientDAO {

	PatientDTO getPatientById(String id);

	int getManyNewPatients();

	List<PatientDTO> getNewPatients();

	List<PatientDTO> getPatients();

}
