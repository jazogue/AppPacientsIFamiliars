package cat.tecnocampus.AppPacFam.persistence;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.domain.Translation.TranslationIdiom;

@Repository
public class StateDAO implements cat.tecnocampus.AppPacFam.application.StateDAO {

	private JdbcTemplate jdbcTemplate;

	public StateDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<StateDTO> statesRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("startTime")
			.newResultSetExtractor(StateDTO.class);

	ResultSetExtractorImpl<StateDTO> statesRowMapperDefault = JdbcTemplateMapperFactory.newInstance().addKeys("stateId")
			.newResultSetExtractor(StateDTO.class);

	RowMapperImpl<StateDTO> stateRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("stateId")
			.newRowMapper(StateDTO.class);

	@Override
	public List<StateDTO> getStatesByAdmissionId(String id, String idiom) {
		final var query = "select state.stateId, treatment_event.eventId, translation.translatedText, state.stateType, treatment_event.startTime from translation inner join state on translation.stateId = state.stateId inner join treatment_event on "
				+ "state.stateId = treatment_event.stateId inner join admission on treatment_event.admissionId = admission.admissionId where admission.admissionId = ? AND translation.translationIdiom = ? OR admission.admissionId = ? AND translation.translationIdiom = 'any'";

		var result = jdbcTemplate.query(query, statesRowMapper, id, idiom, id);
		result.sort((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));
		return result;
	}

	@Override
	public JsonObject setNewGenericState(StateDTO state) {
		final var query1 = "INSERT INTO state (stateId, stateType, locationId) VALUES (?, ?, ?)";
		jdbcTemplate.update(query1, state.getStateId(), state.getStateType().toString(), state.getLocation());
		
		return JsonParser.parseString("{'id': '" + state.getStateId() + "'}").getAsJsonObject();
	}

	@Override
	public void setNewGenericStateToPatient(String stateId, String admissionId) {
		final var query2 = "INSERT INTO treatment_event (eventId, startTime, admissionId, stateId) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(query2, UUID.randomUUID().toString(), new Date(), admissionId, stateId);
	}

	@Override
	public void setNewCustomStateToPatient(StateDTO state, String admissionId) {
		String stateId = UUID.randomUUID().toString();

		final var query1 = "INSERT INTO state (stateId, stateType, locationId) VALUES (?, ?, ?)";
		final var query2 = "INSERT INTO treatment_event (eventId, startTime, admissionId, stateId) VALUES (?, ?, ?, ?)";
		final var query3 = "INSERT INTO translation (translationId, translatedText, translationIdiom, stateId) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(query1, stateId, state.getStateType().toString(), state.getLocation());
		jdbcTemplate.update(query2, UUID.randomUUID().toString(), new Date(), admissionId, stateId);
		jdbcTemplate.update(query3, UUID.randomUUID().toString(), state.getTranslatedText(),
				TranslationIdiom.any.toString(), stateId);

	}

	@Override
	public List<StateDTO> getTypedStates(boolean type, String idiom) {
		var query = "";
		if (type)
			query = "SELECT state.stateId, translation.translatedText, state.stateType from state right outer join translation on state.stateId = translation.stateId WHERE (state.stateType = 'generic' AND translation.translationIdiom = ?);";
		else
			query = "SELECT state.stateId, translation.translatedText, state.stateType from state right outer join translation on state.stateId = translation.stateId WHERE (state.stateType = 'personalitzat' AND translation.translationIdiom = ?);";

		var result = jdbcTemplate.query(query, statesRowMapper, idiom);

		return result;
	}

}
