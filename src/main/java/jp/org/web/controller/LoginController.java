package jp.org.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.org.web.form.LoginForm;
import jp.org.web.message.LoginMessage;
import jp.org.web.repository.LoginRepository;



/**
 * Handles requests for the application home page.
 */

//コントローラであることを記載するアノテーション
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginRepository loginRepository;

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
	public String doLogin(@Validated LoginForm loginForm, BindingResult result) {
		logger.info("Login - doLogin start");
		logger.info("loginId  -> " + loginForm.getLoginId());
		logger.info("password -> " + loginForm.getPassword());

		String ret = "login";

		// 入力チェック
		if(result.hasErrors()) {
			return ret;
		}

		// DBから取得
		String loginResult = loginRepository.getUserMap(loginForm.getLoginId(), loginForm.getPassword());

		if(loginResult != null) {
			logger.info("Login OK, Next Page is home");
			ret = "redirect:/01_list/list";
		} else {
			logger.info("Login NG, Back loin page");
			loginForm.setLoginId("");
			loginForm.setPassword("");
			loginForm.setErrorMessage(LoginMessage.idPassUnmach);
		}

		logger.info("Login - doLogin stop");

		return ret;
	}


}