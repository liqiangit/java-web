package com.hawkingfoo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.hawkingfoo.model.GroupDetail;

@Component
@Mapper
public interface GroupDetailMapper {
	@Select("delete FROM GROUP_DETAIL where ID=#{id}")
	int deleteByPrimaryKey(String id);

	@Insert("INSERT INTO GROUP_DETAIL (ID,CHN,LIBRARY_NAME1,LIBRARY_NAME2,FEATURE_ID,PEOPLE_ID,CATEGORY,X,Y) "
			+ "VALUES (#{id}, #{chn}, #{libraryName1}, #{libraryName2}, #{featureId}, #{peopleId}, #{category}, #{x}, #{y})")
	int insert(GroupDetail record);

	// int insertSelective(GroupDetail record);
	@Select("SELECT * FROM GROUP_DETAIL where ID=#{id}")
	GroupDetail selectByPrimaryKey(String id);

	@Select("SELECT * FROM GROUP_DETAIL where CHN=#{chn} AND LIBRARY_NAME1=#{libraryName1} AND LIBRARY_NAME2=#{libraryName2} AND FEATURE_ID=#{featureId}")
	GroupDetail selectByUK(GroupDetail detail);

	// int updateByPrimaryKeySelective(GroupDetail record);
	@Update("UPDATE GROUP_DETAIL SET PEOPLE_ID=#{peopleId} , CATEGORY=#{category} , X=#{x} , Y=#{y}  where ID=#{id}")
	int updateByPrimaryKey(GroupDetail record);
}