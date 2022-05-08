package cat.tecnocampus.AppPacFam.api;

import org.springframework.web.bind.annotation.*;

import cat.tecnocampus.AppPacFam.application.AppPacFamController;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PatientRestController {

	private AppPacFamController appPacFamController;

	public PatientRestController(AppPacFamController appPacFamController) {
		this.appPacFamController = appPacFamController;
	}

	@GetMapping("/patients")
	public List<PatientDTO> getPatients() {
		return appPacFamController.getPatients();
	}

	@GetMapping("/patient/{id}")
	public PatientDTO getPatientById(@PathVariable String id) {
		return appPacFamController.getPatientById(id);
	}

	@GetMapping("/patients/new/int")
	public int getManyNewPatients() {
		return appPacFamController.getManyNewPatients();
	}

	@GetMapping("/patients/new/object")
	public List<PatientDTO> getNewPatients() {
		return appPacFamController.getNewPatients();
	}

	@GetMapping("/states")
	public List<StateDTO> getStates() {
		return appPacFamController.getStates();
	}

	@GetMapping("/states/patient/{id}")
	public List<StateDTO> getStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getStatesByPatientId(id);
	}
	
	@GetMapping("/states/patient/{id}/gen")
	public List<StateDTO> getGenericStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getTypedStatesByPatientId(id, true);
	}
	
	@GetMapping("/states/patient/{id}/per")
	public List<StateDTO> getCustomStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getTypedStatesByPatientId(id, false);
	}

	@GetMapping("/states/patient/{id}/new/int")
	public int getManyNewStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getManyNewStatesByPatientId(id);
	}

	@GetMapping("/states/patient/{id}/new/object")
	public List<StateDTO> getNewStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getNewStatesByPatientId(id);
	}

	@GetMapping("/patient/summary/{id}")
	public PatientDTO getPatientSummaryById(@PathVariable String id) {
		return appPacFamController.getPatientSummaryById(id);
	}

	@PostMapping("/patient")
	public void postStudent(@RequestBody @Valid PatientDTO patient) {
		appPacFamController.setNewPatient(patient);
	}
	
	@PostMapping("/state/gen")
    public void postGenericState(@RequestBody @Valid StateDTO state) {
	 appPacFamController.setNewGenericState(state);
    }
	

	@PostMapping("/state/generic/{stateId}/patient/{patientId}")
    public void setNewGenericStateToPatient(@PathVariable String stateId, @PathVariable String patientId) {
	 appPacFamController.setNewGenericStateToPatient(stateId, patientId);
    }
	
	@PostMapping("/state/custom/patient/{patientId}")
    public void setNewCustomStateToPatient(@RequestBody @Valid StateDTO state, @PathVariable String patientId) {
	 appPacFamController.setNewCustomStateToPatient(state, patientId);
    }
	
	
	
	

}
