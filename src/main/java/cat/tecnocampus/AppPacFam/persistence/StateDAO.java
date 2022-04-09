package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class StateDAO implements cat.tecnocampus.AppPacFam.application.StateDAO {

	private JdbcTemplate jdbcTemplate;

	public StateDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<StateDTO> statesRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("stateId")
			.newResultSetExtractor(StateDTO.class);

	RowMapperImpl<StateDTO> stateRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("stateId")
			.newRowMapper(StateDTO.class);

	@Override
	public List<StateDTO> getStatesByPhaseId(String id) {
		final var query = "select stateId, stateName, startTime from state where phaseId = ?";
		try {
			var result = jdbcTemplate.query(query, statesRowMapper, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

	@Override
	public List<StateDTO> getStates() {
		final var query = "select stateId, stateName, startTime from state";

		var result = jdbcTemplate.query(query, statesRowMapper);
		return result;
	}

	public List<StateDTO> getStatesByPatientId(String id) {
		final var query = "select stateId, stateName, startTime from state inner join op_phase on "
				+ "state.phaseId = op_phase.phaseId inner join patient on op_phase.patientId = patient.patientId where patient.patientId = ?";

		var result = jdbcTemplate.query(query, statesRowMapper, id);
		return result;
	}

	@Override
	public int getManyNewStatesByPatientId(String id) {
		final var query = "SELECT COUNT(state.checked) from state right outer join op_phase on "
				+ "state.phaseId = op_phase.phaseId right outer join patient on op_phase.patientId = patient.patientId WHERE (state.checked = FALSE AND patient.patientId = ?);";

		return jdbcTemplate.queryForObject(query, Integer.class, id);
	}

	@Override
	public List<StateDTO> getNewStatesByPatientId(String id) {
		final var query = "SELECT stateId, stateName, startTime from state right outer join op_phase on "
				+ "state.phaseId = op_phase.phaseId right outer join patient on op_phase.patientId = patient.patientId WHERE (state.checked = FALSE AND patient.patientId = ?);";

		var result = jdbcTemplate.query(query, statesRowMapper, id);

		final var queryUpdate = "UPDATE state SET checked = TRUE WHERE checked = FALSE;";
		jdbcTemplate.update(queryUpdate);

		return result;
	}

}
