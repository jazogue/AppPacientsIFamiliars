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
import cat.tecnocampus.AppPacFam.application.exception.PatientNotFoundException;

@Repository // @Component
public class LocationDAO implements cat.tecnocampus.AppPacFam.application.LocationDAO {

	private JdbcTemplate jdbcTemplate;

	public LocationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<LocationDTO> locationRowMapperLazy = (resultSet, i) -> {
		LocationDTO location = new LocationDTO();

		location.setLocationId(resultSet.getString("locationId"));
		location.setLocationName(resultSet.getString("locationName"));
		location.setEntryTime(resultSet.getDate("entryTime"));
		location.setDepartureTime(resultSet.getDate("departureTime"));

		return location;
	};

	ResultSetExtractorImpl<LocationDTO> locationsRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("locationId").newResultSetExtractor(LocationDTO.class);

	RowMapperImpl<LocationDTO> locationRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("locationId")
			.newRowMapper(LocationDTO.class);

	@Override
	public List<LocationDTO> getLocations() {
		final var query = "SELECT locationId, locationName, entryTime, departureTime FROM location";

		var result = jdbcTemplate.query(query, locationsRowMapper);
		return result;
	}

	@Override
	public List<LocationDTO> getLocationsByPatientId(String id) {
		final var query = "select * from location where patientId = ?";
		try {
			var result = jdbcTemplate.query(query, locationRowMapperLazy, id);
			return result;
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException(id);
		}
	}

	@Override
	public int getManyNewLocationsByPatientId(String id) {
		final var query = "SELECT COUNT(checked) FROM location WHERE (checked = FALSE AND patientId = ?);";

		return jdbcTemplate.queryForObject(query, Integer.class, id);
	}

	@Override
	public List<LocationDTO> getNewLocationsByPatientId(String id) {
		final var query = "SELECT locationId, locationName, entryTime, departureTime"
				+ " FROM location WHERE (checked = FALSE AND patientId = ?) FOR UPDATE;";

		List<LocationDTO> locations = jdbcTemplate.query(query, locationsRowMapper, id);

		final var queryUpdate = "UPDATE location SET checked = TRUE WHERE (checked = FALSE AND patientId = ?);";
		jdbcTemplate.update(queryUpdate, id);

		return locations;
	}

	@Override
	public void setNewLocation(@Valid LocationDTO location, String patientId) {
		final var query = "INSERT INTO location (locationId, locationName, entryTime, departureTime, patientId) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, location.getLocationId(), location.getLocationName(), location.getEntryTime(), location.getDepartureTime(), patientId);
	}

}
