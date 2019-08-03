package jp.org.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.org.web.form.LoginForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@ModelAttribute
	public LoginForm setLoginForm() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */



	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Login screen display");

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(LoginForm loginForm) {
		logger.info("Login - doLogin start");

		String ret = "login";
		if(loginForm.getLoginId().equals("abc") && loginForm.getPassword().equals("zzz")) {
			ret = "home";
		}else {
		logger.info("Login - doLogin stop");
		loginForm.setLoginId("");
		loginForm.setPassword("");
		}
		return ret;
	}

}