package jp.org.web.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface LoginRepository {
    @Select("select loginId from samurai_login where loginId=#{id} and password=#{pass}")
    String getUserMap(@Param("id") String loginId, @Param("pass") String password);
}
