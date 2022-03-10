package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class StateDAO implements cat.tecnocampus.AppPacFam.application.StateDAO {

	private JdbcTemplate jdbcTemplate;

	public StateDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<StateDTO> locationsRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("stateId").newResultSetExtractor(StateDTO.class);

	RowMapperImpl<StateDTO> locationRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("stateId")
			.newRowMapper(StateDTO.class);


	@Override
	public List<StateDTO> getStatesByPhaseId(String id) {
		final var query = "select stateId, stateName, entryTime from state where phaseId = ?";
		try {
			var result = jdbcTemplate.query(query, locationsRowMapper, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

}
