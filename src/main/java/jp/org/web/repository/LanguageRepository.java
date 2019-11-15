package jp.org.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import jp.org.web.form.LanguageForm;

@Component
public interface LanguageRepository {
    @Select("select * from samurai_language")
    List<LanguageForm> getlanguagelist();

    @Select("select * from samurai_language where language=#{selLanguage}")
    LanguageForm getlanguageInfo(@Param("selLanguage") String selLanguage);

}
