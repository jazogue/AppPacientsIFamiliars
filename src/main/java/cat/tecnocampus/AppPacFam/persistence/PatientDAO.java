package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
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

		patient.setPatientId(resultSet.getString("patientId"));
		patient.setPatientName(resultSet.getString("patientName"));
		patient.setFirstSurname(resultSet.getString("firstSurname"));
		patient.setSecondSurname(resultSet.getString("secondSurname"));
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
		final var query = "select patientId, patientName, firstSurname, secondSurname, hospitalCareType from patient";

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
	public int getManyNewPatients() {
		final var query = "SELECT COUNT(checked) FROM patient WHERE checked = FALSE;";

		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public List<PatientDTO> getNewPatients() {
		final var query = "SELECT patientId, patientName, firstSurname, secondSurname, hospitalCareType"
				+ " FROM patient WHERE checked = FALSE FOR UPDATE;";

		List<PatientDTO> patients = jdbcTemplate.query(query, patientsRowMapper);

		final var queryUpdate = "UPDATE patient SET checked = TRUE WHERE checked = FALSE;";
		jdbcTemplate.update(queryUpdate);

		return patients;
	}

	@Override
	public void setNewPatient(PatientDTO patient) {
		final var query = "INSERT INTO patient (patientId, patientName, firstSurname, secondSurname, hospitalCareType) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, patient.getPatientId(), patient.getPatientName(), patient.getFirstSurname(), patient.getSecondSurname(), patient.getHospitalCareType().toString());
	}

}
