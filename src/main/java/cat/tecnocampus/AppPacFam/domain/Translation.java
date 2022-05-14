package cat.tecnocampus.AppPacFam.domain;

import java.util.UUID;

public class Translation {
	public enum TranslationIdiom {
		ca, es, en, any
	}

	private String translationId;
	private String translatedText;
	private TranslationIdiom translationIdiom;

	public Translation() {
		this.translationId = UUID.randomUUID().toString();
	}

	public String getTranslationId() {
		return translationId;
	}

	public void setTranslationId(String translationId) {
		this.translationId = translationId;
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
