package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class PhaseDAO implements cat.tecnocampus.AppPacFam.application.PhaseDAO {

	private JdbcTemplate jdbcTemplate;

	public PhaseDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<PhaseDTO> phasesRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("phaseId").newResultSetExtractor(PhaseDTO.class);

	RowMapperImpl<PhaseDTO> phaseRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("phaseId")
			.newRowMapper(PhaseDTO.class);


	@Override
	public List<PhaseDTO> getPhasesByPatientId(String id) {
		final var query = "select phaseId, phaseName, startTime, finishedTime from op_phase where patientId = ?";
		try {
			var result = jdbcTemplate.query(query, phasesRowMapper, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

}
