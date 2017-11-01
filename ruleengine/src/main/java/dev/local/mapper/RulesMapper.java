package dev.local.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RulesMapper {

	// @Select("select * from city where state = #{state}")
	// City findByState(@Param("state") String state);

}
