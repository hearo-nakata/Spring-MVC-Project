package jp.org.web.controller;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.org.web.form.LanguageForm;
import jp.org.web.form.LessonlistForm;
import jp.org.web.repository.LanguageRepository;
import jp.org.web.repository.LessonListRepository;



/**
 * Handles requests for the application home page.
 */

//コントローラであることを記載するアノテーション
@Controller
public class UpdateController {

	private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);

	@Autowired
	private LessonListRepository lessonlistrepository;

	@Autowired
	private LanguageRepository languagerepository;

	@ModelAttribute
	public LessonlistForm setlessonlistForm() {
		LessonlistForm lessonlistForm = new LessonlistForm();
		return lessonlistForm;
	}

	@ModelAttribute
	public LanguageForm setlanguageForm() {
		LanguageForm languageForm = new LanguageForm();
		return languageForm;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/02_update/update/{id}", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @PathVariable String id) {
		logger.info("Update screen display");
		logger.info("userId -> " + id);

		List<LanguageForm> languageForm = languagerepository.getlanguagelist();
		model.addAttribute("language", languageForm);

		logger.info("language -> " + languageForm.get(0).getLanguage());

		LessonlistForm lessonDataForm = lessonlistrepository.getLessonData(id);
		model.addAttribute("lessonlistForm", lessonDataForm);

		return "/02_update/update";
	}

	@RequestMapping(value = "/02_update/update/{id}", method = RequestMethod.POST)
	public String updateData(@PathVariable String id, Model model, LessonlistForm lessonlistForm, RedirectAttributes attr) {
		logger.info("update data");

		//初期値としてupdateを持たせる
		String funcType = "update";

		if(lessonlistForm.isDeleteFlg()) {
			lessonlistrepository.deleteLessonData(id);
		//削除の場合はdeleteを代入
		funcType = "delete";
		}else {
			lessonlistrepository.update(lessonlistForm.getUserFirstName(), lessonlistForm.getUserLastName(), lessonlistForm.getLesson1st(), lessonlistForm.getLesson2nd(), id);
		}

		//URLのパラメータで更新か削除かをセットする
		attr.addAttribute("funcType", funcType);

		return "redirect:/01_list/list";

	}

}