package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientAlreadyExistsException;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;
import cat.tecnocampus.AppPacFam.domain.Patient;

@Repository // @Component
public class PatientDAO implements cat.tecnocampus.AppPacFam.application.PatientDAO {

	private JdbcTemplate jdbcTemplate;

	public PatientDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<PatientDTO> patientRowMapperLazy = (resultSet, i) -> {

		PatientDTO patient = new PatientDTO();
		
		while(this.existPatientById(patient.getPatientId()))
				patient = new PatientDTO();
			
		patient.setPatientId(resultSet.getString("patientId"));
		patient.setPatientName(resultSet.getString("patientName"));
		patient.setFirstSurname(resultSet.getString("firstSurname"));
		patient.setSecondSurname(resultSet.getString("secondSurname"));
		patient.setHealthCardIdentifier(resultSet.getString("healthCardIdentifier"));
		patient.setStates(null);
		patient.setHospitalCareType(Patient.HospitalCareType.valueOf(resultSet.getString("hospitalCareType")));

		return patient;
	};

	ResultSetExtractorImpl<PatientDTO> patientsRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("patientId")
			.newResultSetExtractor(PatientDTO.class);

	RowMapperImpl<PatientDTO> patientRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("patientId")
			.newRowMapper(PatientDTO.class);

	@Override
	public List<PatientDTO> getPatients() {
		final var query = "select patientId, patientName, firstSurname, secondSurname, hospitalCareType, healthCardIdentifier from patient";

		return jdbcTemplate.query(query, patientsRowMapper);
	}

	@Override
	public PatientDTO getPatientById(String id) {
		final var query = "select * from patient where patientId = ?";
		try {
			return jdbcTemplate.queryForObject(query, patientRowMapperLazy, id);
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}
	
	@Override
	public PatientDTO getPatientByAnyCriteria(String value) {
		 var query = "select * from patient where patientId = ?";
		try {
			return jdbcTemplate.queryForObject(query, patientRowMapperLazy, value);
		} catch (EmptyResultDataAccessException e) {
			query = "select * from patient where healthCardIdentifier = ?";
			try {
				return jdbcTemplate.queryForObject(query, patientRowMapperLazy, value);
			} catch (EmptyResultDataAccessException e2) {
				throw new PatientNotFoundException(value);
			}
		}
		
	}

	@Override
	public int getManyNewPatients() {
		final var query = "SELECT COUNT(checked) FROM patient WHERE checked = FALSE;";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public List<PatientDTO> getNewPatients() {
		final var query = "SELECT patientId, patientName, firstSurname, secondSurname, hospitalCareType, healthCardIdentifier"
				+ " FROM patient WHERE checked = FALSE FOR UPDATE;";

		List<PatientDTO> patients = jdbcTemplate.query(query, patientsRowMapper);

		final var queryUpdate = "UPDATE patient SET checked = TRUE WHERE checked = FALSE;";
		jdbcTemplate.update(queryUpdate);

		return patients;
	}

	@Override
	public JsonObject setNewPatient(PatientDTO patient) {
		if(existPatientByHealthCardIdentifier(patient.getHealthCardIdentifier())) {
				throw new PatientAlreadyExistsException(patient.getHealthCardIdentifier());
		}
		
		final var query = "INSERT INTO patient (patientId, patientName, firstSurname, secondSurname, hospitalCareType, healthCardIdentifier) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, patient.getPatientId(), patient.getPatientName(), patient.getFirstSurname(),
				patient.getSecondSurname(), patient.getHospitalCareType().toString(), patient.getHealthCardIdentifier());
		
		return JsonParser.parseString("{'id': '"+ patient.getPatientId() +"'}").getAsJsonObject();
	}
	
	private boolean existPatientById(String patientId) {
		final var query = "select * from patient where patientId = ?";
		try {
			jdbcTemplate.queryForObject(query, patientRowMapperLazy, patientId);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}
	
	private boolean existPatientByHealthCardIdentifier(String healthCardIdentifier) {
		final var query = "select * from patient where healthCardIdentifier = ?";
		try {
			jdbcTemplate.queryForObject(query, patientRowMapperLazy, healthCardIdentifier);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public void modifyPatient(PatientDTO patient) {
		final var query = "UPDATE patient SET patientName = ?, firstSurname = ?, secondSurname = ?, hospitalCareType = ?, healthCardIdentifier = ?  WHERE patientId = ?";
		jdbcTemplate.update(query, patient.getPatientName(), patient.getFirstSurname(), patient.getSecondSurname(),
				patient.getHospitalCareType().toString(), patient.getHealthCardIdentifier(), patient.getPatientId());
	}

}
