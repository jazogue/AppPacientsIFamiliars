package cat.tecnocampus.AppPacFam.application.exception;

public class PatientAlreadyExistsException extends RuntimeException {
	public PatientAlreadyExistsException(String healthCardIdentifier) {
		super("Patient with health card identifier: " + healthCardIdentifier + " already exists");
	}
}
