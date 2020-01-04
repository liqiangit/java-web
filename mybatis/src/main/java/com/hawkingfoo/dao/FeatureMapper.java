package com.hawkingfoo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.parser.Feature;

@Component
@Mapper
public interface FeatureMapper {
	@Select("delete FROM FEATURE where ID=#{id}")
	int deleteByPrimaryKey(String id);

	@Insert("INSERT INTO FEATURE (ID,PEOPLE_ID,CATEGORY) VALUES (#{id}, #{peopleId}, #{category})")
	int insert(Feature record);

	// int insertSelective(Feature record);
	@Select("select * FROM FEATURE where ID=#{id}")
	Feature selectByPrimaryKey(String id);

	// int updateByPrimaryKeySelective(Feature record);
	@Update("UPDATE FEATURE SET PEOPLE_ID=#{peopleId},CATEGORY=#{category} WHERE ID=#{id}")
	int updateByPrimaryKey(Feature record);
}