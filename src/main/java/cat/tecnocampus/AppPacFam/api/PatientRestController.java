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

	@PostMapping("/admission/finishDate/{admissionId}")
	public void addFinishDate(@PathVariable String admissionId) {
		appPacFamController.addFinishDate(admissionId);
	}

	@PostMapping("/admission/type/patient/{patientId}")
	public void modifyAdmissionTypeByPatientId(@PathVariable String patientId) {
		appPacFamController.modifyAdmissionTypeByPatientId(patientId);
	}

	// STATES

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

	@PostMapping("/state/generic")
	public JsonObject postGenericState(@RequestBody @Valid StateDTO state) {
		return appPacFamController.setNewGenericState(state);
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
	public JsonObject postLocation(@RequestBody @Valid LocationDTO location) {
		return appPacFamController.setNewLocation(location);
	}

	// TRANSLATIONS
	@PostMapping("/translation/state/{stateId}")
	public JsonObject postTranslation(@RequestBody @Valid TranslationDTO translation, @PathVariable String stateId) {
		return appPacFamController.setNewTranslation(translation, stateId);
	}

	@GetMapping("/translations/{stateId}")
	public List<TranslationDTO> getTranslationsByStateId(@PathVariable String stateId) {
		return appPacFamController.getTranslationsByStateId(stateId);
	}

}
