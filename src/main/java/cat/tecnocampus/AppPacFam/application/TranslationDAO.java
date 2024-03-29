package cat.tecnocampus.AppPacFam.application;

import java.util.List;

import com.google.gson.JsonObject;

import cat.tecnocampus.AppPacFam.application.dto.TranslationDTO;

public interface TranslationDAO {

	JsonObject setNewTranslation(TranslationDTO translation, String stateId);

	List<TranslationDTO> getTranslationsByStateId(String stateId);

}
