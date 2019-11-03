package com.xiaobi.dao;

import com.xiaobi.mybatis.Select;

import java.util.List;

public interface UserDao {
	@Select("select * from User where user_name = ?")
	public List<String> queryUser(String userName);
}
