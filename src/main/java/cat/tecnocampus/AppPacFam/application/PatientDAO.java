package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import javax.validation.Valid;

import com.google.gson.JsonObject;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;

public interface PatientDAO {

	PatientDTO getPatientById(String id);

	PatientDTO getPatientByAnyCriteria(String value);

	List<PatientDTO> getPatients();

	JsonObject setNewPatient(PatientDTO patient);

	void modifyPatient(PatientDTO patient);

}
