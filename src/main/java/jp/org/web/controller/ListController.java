package jp.org.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.org.web.form.LessonlistForm;
import jp.org.web.repository.LessonListRepository;



/**
 * Handles requests for the application home page.
 */

//コントローラであることを記載するアノテーション
@Controller
public class ListController {

	private static final Logger logger = LoggerFactory.getLogger(ListController.class);

	@Autowired
	private LessonListRepository lessonnListRepository;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/01_list/list", method = RequestMethod.GET)
    public String home(Model model) {
        logger.info("List screen display");

      /*  過去無理やりリストの中身を入力して出力していた際の実装
        List<LoginForm> listList = new ArrayList<>();
        LoginForm loginForm = new LoginForm();
        LoginForm loginForm2 = new LoginForm();
        loginForm.setLoginId("sakae");
        loginForm.setPassword("pass");
        loginForm2.setLoginId("hogehoge");
        loginForm2.setPassword("fumufumu");
        listList.add(loginForm);
        listList.add(loginForm2);
                model.addAttribute("listForms", listList);
        */

        List<LessonlistForm> lessonListForm = lessonnListRepository.getLessonListMap();
        model.addAttribute("lessonList", lessonListForm);

        return "01_list/list";
    }

}