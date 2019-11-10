package jp.org.web.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		LessonlistForm lessonDataForm = lessonlistrepository.getLessonData(id);

		//lessonlistに入ってるlanguageとlanguageテーブルのinformationを紐付ける(homeメソッドに追加)
        Optional<String> information1 = this.getLanguageInformation(lessonDataForm.getLesson1st(), languageForm);
        Optional<String> information2 = this.getLanguageInformation(lessonDataForm.getLesson2nd(), languageForm);

		// 値があるときだけ下記の処理実施
//		information1.ifPresent(x -> {
//			lessonDataForm.setInformation1st(x);
//		});

        lessonDataForm.setInformation1st(information1.get());
        lessonDataForm.setInformation2nd(information2.get());

        //順序が重要 model.attributeは最後
		model.addAttribute("language", languageForm);
		model.addAttribute("LessonListForm", lessonDataForm);

		return "/02_update/update";
	}

	//新規ボタン押下で画面遷移してきた時のメソッド
	@RequestMapping(value = "/02_update/update/addRow", method = RequestMethod.GET)
	public String initNewRow(Locale locale, Model model) {
		logger.info("Update screen display new row");

		String newUserId = String.format("%03d", this.getAddRowNo());
		LessonlistForm lessonDataForm = new LessonlistForm();
		lessonDataForm.setUserId(newUserId);
		lessonDataForm.setInsertFlg(true);
		model.addAttribute("LessonListForm", lessonDataForm);

		List<LanguageForm> languageForm = languagerepository.getlanguagelist();
		model.addAttribute("language", languageForm);

		return "/02_update/update";
	}

    @RequestMapping(value = "/02_update/update/getInformation", method = RequestMethod.GET)
    @ResponseBody
    public String getInformation(@RequestParam("language") String language) throws JsonMappingException, JsonProcessingException {
        logger.info("Start getInformation");
        LanguageForm languageForm = languagerepository.getlanguageInfo(language);

        ObjectMapper mapper = new ObjectMapper();
        String ret = mapper.writeValueAsString(languageForm);
        logger.info("End getInformation");
        return ret;
    }

	@RequestMapping(value = "/02_update/update/{path}", method = RequestMethod.POST)
	public String updateData(@PathVariable String path, Model model, LessonlistForm lessonlistForm, RedirectAttributes attr) {
		logger.info("update data");

		//初期値としてupdateを持たせる
		String funcType = "update";

		//削除の場合
		if(lessonlistForm.isDeleteFlg()) {
			lessonlistrepository.deleteLessonData(lessonlistForm.getUserId());
		//deleteを代入
		funcType = "delete";
		//新規作成の場合
		} else if(lessonlistForm.isInsertFlg()) {
			lessonlistrepository.insert(lessonlistForm.getUserId(), lessonlistForm.getUserFirstName(), lessonlistForm.getUserLastName(), lessonlistForm.getLesson1st(), lessonlistForm.getLesson2nd());
			//insertを代入
			funcType = "insert";
		}else {
			lessonlistrepository.update(lessonlistForm.getUserId(), lessonlistForm.getUserFirstName(), lessonlistForm.getUserLastName(), lessonlistForm.getLesson1st(), lessonlistForm.getLesson2nd());
		}

		//URLのパラメータで更新か削除か新規かをセットする
		attr.addAttribute("funcType", funcType);

		return "redirect:/01_list/list";

	}

	//空いてるuserId探すメソッド
	private int getAddRowNo() {
		List<LessonlistForm> list = lessonlistrepository.getLessonListMap();
		int newRowId = 1;
		for(LessonlistForm lessonForm: list) {
			int userIdInt = Integer.parseInt(lessonForm.getUserId());
			if(userIdInt != newRowId) {
				return newRowId;
			}
			newRowId++;
		}

		return newRowId;
	}

	//LessonListテーブルから取得したlanguageとLanguageテーブルから取得したlanguageを紐付けてinformationを取得する
    private Optional<String> getLanguageInformation(String language, List<LanguageForm> languageForm) {
        Optional<String> information = languageForm.stream().filter(x -> x.getLanguage().equals(language)).map(x -> x.getInformation()).findFirst();

        return information;
    }

}