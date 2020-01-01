package com.hawkingfoo.dao;

import com.hawkingfoo.model.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PersonMapper {
    @Insert("INSERT INTO person (name, cities) VALUES (#{name}, #{cities})")
    int insert(Person person);

    @Select("SELECT * FROM person")
    List<Person> selectAll();
    
    @Select("SELECT * FROM person where id=#{id}")
    Person selectById(int id);
    
    @Update("update person set name=#{name}, cities=#{cities} where id=#{id}")
    int update(Person person);
}
