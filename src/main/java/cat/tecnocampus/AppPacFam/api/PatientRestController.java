package cat.tecnocampus.AppPacFam.api;

import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;

import cat.tecnocampus.AppPacFam.application.AppPacFamController;
import cat.tecnocampus.AppPacFam.application.dto.AdmissionDTO;
import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PatientRestController {

	private AppPacFamController appPacFamController;

	public PatientRestController(AppPacFamController appPacFamController) {
		this.appPacFamController = appPacFamController;
	}

	// PATIENTS
	@GetMapping("/patients")
	public List<PatientDTO> getPatients() {
		return appPacFamController.getPatients();
	}

	@GetMapping("/patient/{id}")
	public PatientDTO getPatientById(@PathVariable String id) {
		return appPacFamController.getPatientById(id);
	}

	@GetMapping("/patient/any/{value}")
	public PatientDTO getPatientByAnyCriteria(@PathVariable String value) {
		return appPacFamController.getPatientByAnyCriteria(value);
	}

	@GetMapping("/patient/summary/{id}")
	public PatientDTO getPatientSummaryById(@PathVariable String id) {
		return appPacFamController.getPatientSummaryById(id);
	}

	@PostMapping("/patient")
	public JsonObject postPatient(@RequestBody @Valid PatientDTO patient) {
		return appPacFamController.setNewPatient(patient);
	}

	@PostMapping("/patient/modify")
	public void modifyPatient(@RequestBody @Valid PatientDTO patient) {
		appPacFamController.modifyPatient(patient);
	}

	// ADMISSIONS
	@GetMapping("/admissions/patient/{patientId}")
	public List<AdmissionDTO> getAdmissionsByPatientId(@PathVariable String patientId) {
		return appPacFamController.getAdmissionsByPatientId(patientId);
	}

	@GetMapping("/admission/active/patient/{patientId}")
	public AdmissionDTO getActiveAdmissionByPatientId(@PathVariable String patientId) {
		return appPacFamController.getActiveAdmissionByPatientId(patientId);
	}

	@PostMapping("/admission/patient/{patientId}")
	public void setNewAdmission(@RequestBody @Valid AdmissionDTO admission, @PathVariable String patientId) {
		appPacFamController.setNewAdmission(admission, patientId);
	}

	@PostMapping("/admission/modify")
	public void modifyAdmission(@RequestBody @Valid AdmissionDTO admission) {
		appPacFamController.modifyAdmission(admission);
	}

	@PostMapping("/admission/type/patient/{patientId}")
	public void modifyAdmissionTypeByPatientId(@PathVariable String patientId) {
		appPacFamController.modifyAdmissionTypeByPatientId(patientId);
	}

	// STATES
	@GetMapping("/states")
	public List<StateDTO> getStates() {
		return appPacFamController.getStates();
	}

	@GetMapping("/states/generic/{idiom}")
	public List<StateDTO> getGenericStates(@PathVariable String idiom) {
		return appPacFamController.getTypedStates(true, idiom);
	}

	@GetMapping("/states/custom/{idiom}")
	public List<StateDTO> getCustomStates(@PathVariable String idiom) {
		return appPacFamController.getTypedStates(false, idiom);
	}

	@GetMapping("/states/admission/{id}/idiom/{idiom}")
	public List<StateDTO> getStatesByAdmissionId(@PathVariable String id, @PathVariable String idiom) {
		return appPacFamController.getStatesByAdmissionId(id, idiom);
	}

	@GetMapping("/states/patient/{id}/gen/{idiom}")
	public List<StateDTO> getGenericStatesByPatientId(@PathVariable String id, @PathVariable String idiom) {
		return appPacFamController.getTypedStatesByPatientId(id, true, idiom);
	}

	@GetMapping("/states/patient/{id}/per/{idiom}")
	public List<StateDTO> getCustomStatesByPatientId(@PathVariable String id, @PathVariable String idiom) {
		return appPacFamController.getTypedStatesByPatientId(id, false, idiom);
	}

	@PostMapping("/state/gen")
	public void postGenericState(@RequestBody @Valid StateDTO state) {
		appPacFamController.setNewGenericState(state);
	}

	@PostMapping("/state/generic/{stateId}/admission/{admissionId}")
	public void setNewGenericStateToPatient(@PathVariable String stateId, @PathVariable String admissionId) {
		appPacFamController.setNewGenericStateToPatient(stateId, admissionId);
	}

	@PostMapping("/state/custom/admission/{admissionId}")
	public void setNewCustomStateToPatient(@RequestBody @Valid StateDTO state, @PathVariable String admissionId) {
		appPacFamController.setNewCustomStateToPatient(state, admissionId);
	}

	// LOCATIONS
	@GetMapping("/locations")
	public List<LocationDTO> getAllLocations() {
		return appPacFamController.getAllLocations();
	}

	@GetMapping("/location/{locationId}")
	public LocationDTO getLocationById(@PathVariable String locationId) {
		return appPacFamController.getLocationById(locationId);
	}

	@PostMapping("/location")
	public void postLocation(@RequestBody @Valid LocationDTO location) {
		appPacFamController.setNewLocation(location);
	}

	@GetMapping("/location/{patientId}")
	public List<LocationDTO> getLocationsByPatientId(@PathVariable String patientId) {
		return appPacFamController.getLocationsByPatientId(patientId);
	}

	// TRANSLATIONS
	@PostMapping("/translation/state/{stateId}")
	public void postTranslation(@RequestBody @Valid TranslationDTO translation, @PathVariable String stateId) {
		appPacFamController.setNewTranslation(translation, stateId);
	}

	@GetMapping("/translations/{stateId}")
	public List<TranslationDTO> getTranslationsByStateId(@PathVariable String stateId) {
		return appPacFamController.getTranslationsByStateId(stateId);
	}

}
