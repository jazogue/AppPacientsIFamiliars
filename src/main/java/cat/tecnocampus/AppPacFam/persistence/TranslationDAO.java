package cat.tecnocampus.AppPacFam.persistence;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.ResultSetExtractorImpl;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

@Repository
public class TranslationDAO implements cat.tecnocampus.AppPacFam.application.TranslationDAO {

	private JdbcTemplate jdbcTemplate;

	public TranslationDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	ResultSetExtractorImpl<TranslationDTO> translationsRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("translationId").newResultSetExtractor(TranslationDTO.class);

	RowMapperImpl<TranslationDTO> translationRowMapper = JdbcTemplateMapperFactory.newInstance()
			.addKeys("translationId").newRowMapper(TranslationDTO.class);

	@Override
	public void setNewTranslation(TranslationDTO translation, String stateId) {
		final var query = "INSERT INTO translation (translationId, translatedText, translationIdiom, stateId) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(query, translation.getTranslationId(), translation.getTranslatedText(),
				translation.getTranslationIdiom().toString(), stateId);
	}

	@Override
	public List<TranslationDTO> getTranslationsByStateId(String stateId) {
		final var query = "select translation.translationId, translation.translatedText, translation.translationIdiom from translation right outer join state on translation.stateId = state.stateId where state.stateId = ?";
		return jdbcTemplate.query(query, translationsRowMapper, stateId);
	}
}
