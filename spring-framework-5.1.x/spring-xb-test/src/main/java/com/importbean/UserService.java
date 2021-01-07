package com.importbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public void query(){
		userDao.query("xiaobi");
	}
}
