package com.dev.mapper;

import com.dev.pojo.User;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper //两种方式，一种各个mapper类上的@Mapper标明mapper 一种是启动类@MapperScan扫描。（一般用后者包扫描，一次配置即可搞定，避免每个mapper都去声明）
public interface UserMapper {

	User queryUserById(Long id);

	/**
	 * 实际上mybatis是支持使用注解sql来代替xml配置sql,
	 * 但这种方式不太方便,通常还是编写xml配置sql.
	 * 下面示例mybatis的注解配置sql但是替代xml
	 */
	@Select("select *,user_name userName from tb_user")
	List<User> getListUser();

}
