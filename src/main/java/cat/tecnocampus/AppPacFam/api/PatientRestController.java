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
	
	@GetMapping("/patient/summary/{id}")
	public PatientDTO getPatientSummaryById(@PathVariable String id) {
		return appPacFamController.getPatientSummaryById(id);
	}
	
	@GetMapping("/locations/patient/{id}")
	public List<LocationDTO> getLocationsByPatientId(@PathVariable String id) {
		return appPacFamController.getLocationsByPatientId(id);
	}
	
	/*
	@GetMapping("/locations/patient/{id}/datetime/{datetime}")
	public List<LocationDTO> getLocationsFromSpecificDateTimePatientId(@PathVariable String id, @PathVariable String dateTime) {
		return appPacFamController.getLocationsFromSpecificDateTimePatientId(id, dateTime);
	}
	*/
	
	@GetMapping("/phases/patient/{id}")
	public List<PhaseDTO> getPhasesByPatientId(@PathVariable String id) {
		return appPacFamController.getPhasesByPatientId(id);
	}

	
	@GetMapping("/phase/states/{id}")
	public List<StateDTO> getStatesByPhaseId(@PathVariable String id) {
		return appPacFamController.getStatesByPhaseId(id);
	}
	

}
