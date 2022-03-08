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

	@GetMapping("/patient/{id}")
	public PatientDTO getPatientById(@PathVariable String id) {
		return appPacFamController.getPatientById(id);
	}
	
	@GetMapping("/patient/{id}/locations")
	public List<LocationDTO> getLocationsByPatientId(@PathVariable String id) {
		return appPacFamController.getLocationsByPatientId(id);
	}
	
	@GetMapping("/patient/{id}/phases")
	public List<PhaseDTO> getPhasesByPatientId(@PathVariable String id) {
		return appPacFamController.getPhasesByPatientId(id);
	}

	
	@GetMapping("/patient/{id}/states")
	public List<StateDTO> getStatesByPatientId(@PathVariable String id) {
		return appPacFamController.getStatesByPatientId(id);
	}
	

}
