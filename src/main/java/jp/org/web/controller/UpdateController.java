package jp.org.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.org.web.form.LessonlistForm;
import jp.org.web.repository.LessonListRepository;



/**
 * Handles requests for the application home page.
 */

//コントローラであることを記載するアノテーション
@Controller
public class UpdateController {

	private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);

	@Autowired
	private LessonListRepository repository;

	@ModelAttribute
	public LessonlistForm setLessonlistForm() {
		LessonlistForm lessonlistForm = new LessonlistForm();
		return lessonlistForm;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/02_update/update/{id}", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @PathVariable String id) {
		logger.info("Update screen display");
		logger.info("userId -> " + id);

		LessonlistForm lessonDataForm = repository.getLessonData(id);
		model.addAttribute("LessonlistForm", lessonDataForm);

		return "/02_update/update";
	}

	@RequestMapping(value = "/02_update/update/{id}", method = RequestMethod.POST)
	public String updateData(@PathVariable String id, Model model, LessonlistForm lessonlistForm) {
		logger.info("update data");

		repository.update(lessonlistForm.getUserFirstName(), lessonlistForm.getUserLastName(), lessonlistForm.getLesson1st(), lessonlistForm.getLesson2nd(), id);

		return "/02_update/update";
	}

}