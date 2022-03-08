package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.dto.PatientDTO;
import cat.tecnocampus.AppPacFam.application.dto.PhaseDTO;
import cat.tecnocampus.AppPacFam.application.dto.StateDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository //@Component
public class PatientDAO implements cat.tecnocampus.AppPacFam.application.PatientDAO {

	private JdbcTemplate jdbcTemplate;
	
	public PatientDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	ResultSetExtractorImpl<PatientDTO> patientsRowMapper =
			JdbcTemplateMapperFactory
					.newInstance()
					.addKeys("patientId")
					.newResultSetExtractor(PatientDTO.class);

	RowMapperImpl<PatientDTO> patientRowMapper =
			JdbcTemplateMapperFactory
					.newInstance()
					.addKeys("patientId")
					.newRowMapper(PatientDTO.class);
	
	@Override
	public PatientDTO getPatientById(String id) {
		final var query = "select * from patient where patientId = ?";
        try {
            return jdbcTemplate.queryForObject(query, patientRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException(id);
        }
	}

	@Override
	public List<LocationDTO> getLocationsByPatientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhaseDTO> getPhasesByPatientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateDTO> getStatesByPatientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateDTO> getStatesByPhasesId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
