package jp.org.web.form;

import java.io.Serializable;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginId = "";

	private String password = "";

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

}