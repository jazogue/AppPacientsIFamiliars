package cat.tecnocampus.AppPacFam.application;

import cat.tecnocampus.AppPacFam.application.dto.AdmissionDTO;
import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import java.util.List;

import javax.validation.Valid;

@Service // same as @Component
public class AppPacFamController {
	private PatientDAO patientDAO;
	private StateDAO stateDAO;
	private AdmissionDAO admissionDAO;
	private LocationDAO locationDAO;
	private TranslationDAO translationDAO;

	public AppPacFamController(PatientDAO patientDAO, StateDAO stateDAO, AdmissionDAO admissionDAO,
			LocationDAO locationDAO, TranslationDAO translationDAO) {
		this.patientDAO = patientDAO;
		this.stateDAO = stateDAO;
		this.admissionDAO = admissionDAO;
		this.locationDAO = locationDAO;
		this.translationDAO = translationDAO;

	}

	public List<PatientDTO> getPatients() {
		return patientDAO.getPatients();
	}

	public PatientDTO getPatientById(String id) {
		return patientDAO.getPatientById(id);
	}

	public List<StateDTO> getStates() {
		return stateDAO.getStates();
	}

	public List<StateDTO> getStatesByAdmissionId(String id, String idiom) {
		return stateDAO.getStatesByAdmissionId(id, idiom);
	}

	public PatientDTO getPatientSummaryById(String id) {
		PatientDTO patientDto = new PatientDTO();
		patientDto = patientDAO.getPatientById(id);

		return patientDto;
	}

	public JsonObject setNewPatient(PatientDTO patient) {
		return patientDAO.setNewPatient(patient);

	}

	public void setNewGenericState(StateDTO state) {
		stateDAO.setNewGenericState(state);
	}

	public List<StateDTO> getTypedStatesByPatientId(String id, boolean type, String idiom) {
		return stateDAO.getTypedStatesByPatientId(id, type, idiom);
	}

	public void setNewGenericStateToPatient(String stateId, String admissionId) {
		stateDAO.setNewGenericStateToPatient(stateId, admissionId);
	}

	public void setNewCustomStateToPatient(StateDTO state, String admissionId) {
		stateDAO.setNewCustomStateToPatient(state, admissionId);
	}

	public List<StateDTO> getTypedStates(boolean type, String idiom) {
		return stateDAO.getTypedStates(type, idiom);
	}

	public PatientDTO getPatientByAnyCriteria(String value) {
		return patientDAO.getPatientByAnyCriteria(value);
	}

	public void modifyPatient(PatientDTO patient) {
		patientDAO.modifyPatient(patient);
	}

	public List<AdmissionDTO> getAdmissionsByPatientId(String patientId) {
		return admissionDAO.getAdmissionsByPatientId(patientId);
	}

	public AdmissionDTO getActiveAdmissionByPatientId(String patientId) {
		return admissionDAO.getActiveAdmissionByPatientId(patientId);
	}

	public void setNewAdmission(AdmissionDTO admission, String patientId) {
		admissionDAO.setNewAdmission(admission, patientId);
	}

	public void addFinishDate(String admissionId) {
		admissionDAO.addFinishDate(admissionId);
	}

	public void modifyAdmissionTypeByPatientId(String patientId) {
		admissionDAO.modifyAdmissionTypeByPatientId(patientId);
	}

	public List<LocationDTO> getAllLocations() {
		return locationDAO.getAllLocations();
	}

	public void setNewLocation(LocationDTO location) {
		locationDAO.setNewLocation(location);
	}

	public LocationDTO getLocationById(String locationId) {
		return locationDAO.getLocationById(locationId);
	}

	public List<LocationDTO> getLocationsByPatientId(String patientId) {
		return locationDAO.getLocationsByPatientId(patientId);
	}

	public List<TranslationDTO> getTranslationsByStateId(String stateId) {
		return translationDAO.getTranslationsByStateId(stateId);
	}

	public void setNewTranslation(TranslationDTO translation, String stateId) {
		translationDAO.setNewTranslation(translation, stateId);
	}

}
