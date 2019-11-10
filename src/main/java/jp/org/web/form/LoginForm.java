package jp.org.web.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import jp.org.web.message.LoginMessage;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(min = 1, message=LoginMessage.loginIdBlank)
	private String loginId;

	@Size(min = 1, message=LoginMessage.passwordBlank)
	private String password;

	private String errorMessage;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		/*if(loginId != "abc") {
			this.loginId = "";
		}
		*/
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		/*if(password != "def") {
			this.loginId = "";
		}
		*/
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}