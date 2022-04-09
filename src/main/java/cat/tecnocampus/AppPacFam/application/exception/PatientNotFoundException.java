package cat.tecnocampus.AppPacFam.application.exception;

public class PatientNotFoundException extends RuntimeException {
	public PatientNotFoundException(String id) {
		super("Patient with id: " + id + " does not exist");
	}
}
