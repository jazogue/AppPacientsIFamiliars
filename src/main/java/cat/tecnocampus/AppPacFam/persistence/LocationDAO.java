package cat.tecnocampus.AppPacFam.persistence;

import java.text.SimpleDateFormat;
import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class LocationDAO implements cat.tecnocampus.AppPacFam.application.LocationDAO {

	private JdbcTemplate jdbcTemplate;

	public LocationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<LocationDTO> locationsRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("locationId").newResultSetExtractor(LocationDTO.class);

	RowMapperImpl<LocationDTO> locationRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("locationId")
			.newRowMapper(LocationDTO.class);

	@Override
	public List<LocationDTO> getLocationsByPatientId(String id) {
		final var query = "select locationId, locationName, entryTime, departureTime from location where patientId = ? order by departureTime";
		try {
			var result = jdbcTemplate.query(query, locationsRowMapper, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

	/*
	@Override
	public List<LocationDTO> getLocationsFromSpecificDateTimePatientId(String id, String dateTimeNow, String dateTimeLast) {
		SELECT * FROM table WHERE value BETWEEN '15' AND '16' ORDER BY id, time
		final var query = "select locationId, locationName, entryTime, departureTime from location where patientId = " + id + "";
		try {
			var result = jdbcTemplate.query(query, locationsRowMapper, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}
	*/

}
