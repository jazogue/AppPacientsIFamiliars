package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.LocationDTO;

@Repository
public class LocationDAO implements cat.tecnocampus.AppPacFam.application.LocationDAO {

	private JdbcTemplate jdbcTemplate;

	public LocationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	ResultSetExtractorImpl<LocationDTO> locationsRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("locationId")
			.newResultSetExtractor(LocationDTO.class);

	RowMapperImpl<LocationDTO> locationRowMapper = JdbcTemplateMapperFactory.newInstance().addKeys("locationId")
			.newRowMapper(LocationDTO.class);
	
	@Override
	public List<LocationDTO> getAllLocations() {
		final var query = "select locationId, locationName from location";

		return jdbcTemplate.query(query, locationsRowMapper);
	}

}
