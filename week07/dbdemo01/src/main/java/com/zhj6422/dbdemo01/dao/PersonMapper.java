package com.zhj6422.dbdemo01.dao;


import com.zhj6422.dbdemo01.bean.Person;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PersonMapper extends Mapper<Person> {
}
