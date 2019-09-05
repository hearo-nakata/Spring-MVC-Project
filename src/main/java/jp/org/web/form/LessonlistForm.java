package jp.org.web.form;

import java.io.Serializable;

public class LessonlistForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId = "";

	private String userFirstName = "";

	private String userLastName = "";

	private String lesson1st = "";

	private String lesson2nd = "";

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getLesson1st() {
		return lesson1st;
	}

	public void setLesson1st(String lesson1st) {
		this.lesson1st = lesson1st;
	}

	public String getLesson2nd() {
		return lesson2nd;
	}

	public void setLesson2nd(String lesson2nd) {
		this.lesson2nd = lesson2nd;
	}



}