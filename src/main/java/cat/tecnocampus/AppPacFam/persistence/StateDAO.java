package cat.tecnocampus.AppPacFam.persistence;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	public List<StateDTO> getStates() {
		final var query = "select state.stateId, state.stateName, state.stateType, treatment_event.startTime from state inner join treatment_event on "+ 
				"state.stateId = treatment_event.stateId";

		var result = jdbcTemplate.query(query, statesRowMapper);
		result.sort((o1,o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
		return result;
	}

	public List<StateDTO> getStatesByPatientId(String id) {
		final var query = "select state.stateId, state.stateName, state.stateType, treatment_event.startTime from state inner join treatment_event on "
				+ "state.stateId = treatment_event.stateId inner join patient on treatment_event.patientId = patient.patientId where patient.patientId = ?";

		var result = jdbcTemplate.query(query, statesRowMapper, id);
		result.sort((o1,o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
		return result;
	}

	@Override
	public int getManyNewStatesByPatientId(String id) {
		final var query = "SELECT COUNT(state.checked) from state right outer join treatment_event on "
				+ "state.stateId = treatment_event.stateId right outer join patient on treatment_event.patientId = patient.patientId WHERE (state.checked = FALSE AND patient.patientId = ?);";

		return jdbcTemplate.queryForObject(query, Integer.class, id);
	}

	@Override
	public List<StateDTO> getNewStatesByPatientId(String id) {
		final var query = "SELECT state.stateId, state.stateName, state.stateType, treatment_event.startTime from state right outer join treatment_event on "
				+ "state.stateId = treatment_event.stateId right outer join patient on treatment_event.patientId = patient.patientId WHERE (state.checked = FALSE AND patient.patientId = ?);";

		var result = jdbcTemplate.query(query, statesRowMapper, id);

		final var queryUpdate = "UPDATE state SET checked = TRUE WHERE checked = FALSE;";
		jdbcTemplate.update(queryUpdate);

		return result;
	}
	
	public List<StateDTO> getTypedStatesByPatientId(String id, boolean type){
		var query = "";
		if(type)
		 query = "SELECT state.stateId, state.stateName, state.stateType, treatment_event.startTime from state right outer join treatment_event on "
				+ "state.stateId = treatment_event.stateId right outer join patient on treatment_event.patientId = patient.patientId WHERE (state.stateType = 'generic' AND patient.patientId = ?);";
		else
			query = "SELECT state.stateId, state.stateName, state.stateType, treatment_event.startTime from state right outer join treatment_event on "
					+ "state.stateId = treatment_event.stateId right outer join patient on treatment_event.patientId = patient.patientId WHERE (state.stateType = 'personalitzat' AND patient.patientId = ?);";
		var result = jdbcTemplate.query(query, statesRowMapper, id);

		final var queryUpdate = "UPDATE state SET checked = TRUE WHERE checked = FALSE;";
		jdbcTemplate.update(queryUpdate);

		return result;
	}

	@Override
	public void setNewGenericState(StateDTO state) {
		final var query1 = "INSERT INTO state (stateId, stateName, stateType) VALUES (?, ?, ?)";
        jdbcTemplate.update(query1, state.getStateId(), state.getStateName(), state.getStateType().toString());
	}
	

	@Override
	public void setNewGenericStateToPatient(String stateId, String patientId) {
		final var query = "INSERT INTO treatment_event (eventId, startTime, stateId, patientId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, UUID.randomUUID().toString(), new Date(), stateId, patientId);
        
	}
	
	@Override
	public void setNewCustomStateToPatient(StateDTO state, String patientId) {
		final var query1 = "INSERT INTO state (stateId, stateName, stateType) VALUES (?, ?, ?)";
		final var query2 = "INSERT INTO treatment_event (eventId, startTime, stateId, patientId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query1, state.getStateId(), state.getStateName(), state.getStateType().toString());
        jdbcTemplate.update(query2, UUID.randomUUID().toString(), new Date(), state.getStateId(), patientId);
        
	}

}
