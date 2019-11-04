package com.xiaobi.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class IndexBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return new UserBean();
	}

	@Override
	public Class<?> getObjectType() {
		return UserBean.class;
	}
}
