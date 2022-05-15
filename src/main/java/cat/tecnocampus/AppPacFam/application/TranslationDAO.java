package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

public interface TranslationDAO {

	void setNewTranslation(TranslationDTO translation, String stateId);

	List<TranslationDTO> getTranslationsByStateId(String stateId);

}
