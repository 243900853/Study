package com.xiaobi.proxy;
import com.xiaobi.proxy.Service;
public class $Proxy implements Service {
	privateService target;
	public Service(Servicetarget){
		this.target = target;
	}
	public class java.lang.Stringquery(Stringp0){
		System.out.println("打印日志");
		target.query(Stringp);
		return null;
	}
}