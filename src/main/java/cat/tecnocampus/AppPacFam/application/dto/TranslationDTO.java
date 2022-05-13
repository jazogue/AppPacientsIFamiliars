package cat.tecnocampus.AppPacFam.application.dto;

import java.util.UUID;

import cat.tecnocampus.AppPacFam.domain.Translation.TranslationIdiom;

public class TranslationDTO {
	private String locationId;
	private String translatedText;
	private TranslationIdiom translationIdiom;

	public TranslationDTO() {
		this.locationId = UUID.randomUUID().toString();
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	public TranslationIdiom getTranslationIdiom() {
		return translationIdiom;
	}

	public void setTranslationIdiom(TranslationIdiom translationIdiom) {
		this.translationIdiom = translationIdiom;
	}
}
