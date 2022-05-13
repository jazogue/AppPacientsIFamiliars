package cat.tecnocampus.AppPacFam.domain;

import java.util.UUID;

public class Translation {
	public enum TranslationIdiom {
		ca, es, en
	}

	private String locationId;
	private String translatedText;
	private TranslationIdiom translationIdiom;

	public Translation() {
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
