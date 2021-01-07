package com.importbean;

import com.xiaobi.mybatis.Select;

public interface UserDao {
	@Select("select * from user where userName = ?")
	public void query(String userName);
}
