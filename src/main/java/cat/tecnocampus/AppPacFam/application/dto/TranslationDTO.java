package cat.tecnocampus.AppPacFam.application.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cat.tecnocampus.AppPacFam.domain.Translation.TranslationIdiom;

public class TranslationDTO {
	@NotNull(message = "Translation id cannot be null")
	@NotBlank(message = "Translation id cannot be blank")
	private String translationId;
	@NotNull(message = "Translated text cannot be null")
	@NotBlank(message = "Translated text cannot be blank")
	private String translatedText;
	@NotNull(message = "Translation idiom cannot be null")
	private TranslationIdiom translationIdiom;

	public TranslationDTO() {
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
