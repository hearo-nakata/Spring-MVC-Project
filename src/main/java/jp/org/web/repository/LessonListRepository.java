package jp.org.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import jp.org.web.form.LessonlistForm;

@Component
public interface LessonListRepository {
    @Select("select * from samurai_lessonlist")
    List<LessonlistForm> getLessonListMap();
}
