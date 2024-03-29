package cat.tecnocampus.AppPacFam.application;

import cat.tecnocampus.AppPacFam.application.dto.AdmissionDTO;
import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import java.util.List;


@Service
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

	public List<StateDTO> getStatesByAdmissionId(String id, String idiom) {
		return stateDAO.getStatesByAdmissionId(id, idiom);
	}

	public JsonObject setNewPatient(PatientDTO patient) {
		return patientDAO.setNewPatient(patient);
	}

	public JsonObject setNewGenericState(StateDTO state) {
		return stateDAO.setNewGenericState(state);
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

	public JsonObject setNewLocation(LocationDTO location) {
		return locationDAO.setNewLocation(location);
	}

	public LocationDTO getLocationById(String locationId) {
		return locationDAO.getLocationById(locationId);
	}

	public List<TranslationDTO> getTranslationsByStateId(String stateId) {
		return translationDAO.getTranslationsByStateId(stateId);
	}

	public JsonObject setNewTranslation(TranslationDTO translation, String stateId) {
		return translationDAO.setNewTranslation(translation, stateId);
	}

}
