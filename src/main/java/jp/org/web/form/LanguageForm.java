package jp.org.web.form;

import java.io.Serializable;

public class LanguageForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String language = "";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}

//test