package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import javax.validation.Valid;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class PhaseDAO implements cat.tecnocampus.AppPacFam.application.PhaseDAO {

	private JdbcTemplate jdbcTemplate;

	public PhaseDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<PhaseDTO> phaseRowMapperLazy = (resultSet, i) -> {
		PhaseDTO phase = new PhaseDTO();

		phase.setPhaseId(resultSet.getString("phaseId"));
		phase.setPhaseName(resultSet.getString("phaseName"));
		phase.setStartTime(resultSet.getDate("startTime"));
		phase.setFinishedTime(resultSet.getDate("finishedTime"));
		phase.setHospitalCareType(resultSet.getInt("hospitalCareType"));

		return phase;
	};

	ResultSetExtractorImpl<PhaseDTO> phasesRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("phaseId")
			.newResultSetExtractor(PhaseDTO.class);

	RowMapperImpl<PhaseDTO> phaseRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("phaseId")
			.newRowMapper(PhaseDTO.class);

	@Override
	public List<PhaseDTO> getPhases() {
		final var query = "select phaseId, phaseName, startTime, finishedTime, hospitalCareType from op_phase";

		var result = jdbcTemplate.query(query, phasesRowMapper);
		return result;
	}

	@Override
	public List<PhaseDTO> getPhasesByPatientId(String id) {
		final var query = "select * from op_phase where patientId = ?";
		try {
			var result = jdbcTemplate.query(query, phaseRowMapperLazy, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

	@Override
	public List<PhaseDTO> getNewPhasesByPatientId(String id) {
		final var query = "SELECT * FROM op_phase WHERE (checked = FALSE AND patientId = ?) FOR UPDATE;";

		List<PhaseDTO> phases = jdbcTemplate.query(query, phaseRowMapperLazy, id);

		final var queryUpdate = "UPDATE op_phase SET checked = TRUE WHERE (checked = FALSE AND patientId = ?);";
		jdbcTemplate.update(queryUpdate, id);

		return phases;
	}

	@Override
	public int getManyNewPhasesByPatientId(String id) {
		final var query = "SELECT COUNT(checked) FROM op_phase WHERE (checked = FALSE AND patientId = ?);";

		return jdbcTemplate.queryForObject(query, Integer.class, id);
	}

	@Override
	public void setNewPhase(@Valid PhaseDTO phase,  String patientId) {
		final var query = "INSERT INTO op_phase (phaseId, phaseName, startTime, finishedTime, hospitalCareType, patientId) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, phase.getPhaseId(), phase.getPhaseName(), phase.getStartTime(), phase.getFinishedTime(), phase.getHospitalCareType(), patientId);
	}

}
