package com.rpt.system.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface ConfigMapper {

    @Insert(
            "insert into config(config_id,name) values(#{id},#{name})"
    )
    boolean insert(Map<String, Object> map);

    @Select("select count(1) from config where config_id = #{id}")
    int selectCount(@Param("id") String id);
}
