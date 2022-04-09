package cat.tecnocampus.AppPacFam.api;


import org.springframework.web.bind.annotation.*;

import cat.tecnocampus.AppPacFam.application.AppPacFamController;
import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;

import java.util.List;

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
	
	@GetMapping("/locations")
	public List<LocationDTO> getLocations() {
		return appPacFamController.getLocations();
	}

	@GetMapping("/locations/patient/{id}")
	public List<LocationDTO> getLocationsByPatientId(@PathVariable String id) {
		return appPacFamController.getLocationsByPatientId(id);
	}

	@GetMapping("/locations/patient/{id}/new/int")
	public int getManyNewLocationsByPatientId(@PathVariable String id) {
		return appPacFamController.getManyNewLocationsByPatientId(id);
	}

	@GetMapping("/locations/patient/{id}/new/object")
	public List<LocationDTO> getNewLocationsByPatientId(@PathVariable String id) {
		return appPacFamController.getNewLocationsByPatientId(id);
	}

	@GetMapping("/phases")
	public List<PhaseDTO> getPhases() {
		return appPacFamController.getPhases();
	}
	
	@GetMapping("/phases/patient/{id}")
	public List<PhaseDTO> getPhasesByPatientId(@PathVariable String id) {
		return appPacFamController.getPhasesByPatientId(id);
	}

	@GetMapping("/phases/patient/{id}/new/int")
	public int getManyNewPhasesByPatientId(@PathVariable String id) {
		return appPacFamController.getManyNewPhasesByPatientId(id);
	}

	@GetMapping("/phases/patient/{id}/new/object")
	public List<PhaseDTO> getNewPhasesByPatientId(@PathVariable String id) {
		return appPacFamController.getNewPhasesByPatientId(id);
	}

	@GetMapping("/states")
	public List<StateDTO> getStates() {
		return appPacFamController.getStates();
	}
	
	@GetMapping("/phase/states/{id}")
	public List<StateDTO> getStatesByPhaseId(@PathVariable String id) {
		return appPacFamController.getStatesByPhaseId(id);
	}

	@GetMapping("/patient/summary/{id}")
	public PatientDTO getPatientSummaryById(@PathVariable String id) {
		return appPacFamController.getPatientSummaryById(id);
	}

}
