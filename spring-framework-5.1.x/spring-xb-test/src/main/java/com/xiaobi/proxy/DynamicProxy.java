package com.xiaobi.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicProxy {
	//动态模拟生产LogService内容
	public static Object getInsance(Object target){
		Class clazz = target.getClass().getInterfaces()[0];//获取到实现的接口
		String infName = clazz.getSimpleName();//接口类名
		String content = "";//内容
		String line = "\n";//换行
		String tab = "\t";//tab
		//类所在的包路径 package com.xiaobi.proxy;
		String packageName = DynamicProxy.class.getPackage().getName();
		String packageContent = "package "+packageName+";"+line;
//		String packageContent = "package com.xiaobi.test;"+line;
		//引入接口 import com.xiaobi.proxy.Service;
		String importContent = "import "+clazz.getName()+";"+line;
		//生成java类名 public class LogService implements Service {
		String clazzFirstLineContent = "public class $Proxy implements "+infName+" {"+line;
		//属性内容 Service target;
		String filedContent = tab+"private "+infName+" target;"+line;
		//构造方法内容
		StringBuffer constructorContent = new StringBuffer();
		//public LogService(Service target){
		constructorContent.append(tab+"public $Proxy("+infName+" target){"+line);
		//this.target = target;
		constructorContent.append(tab+tab+"this.target = target;"+line);
		//  }
		constructorContent.append(tab+"}"+line);
		//接口方法内容
		StringBuffer methodContent = new StringBuffer();
		//获取到接口方法的集合
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			//获取接口方法返回类型
			String returnType = method.getReturnType().getSimpleName();
			//获取接口方法名称
			String methodName = method.getName();
			//获取接口方法参数类型
			Class<?>[] parameterTypes = method.getParameterTypes();
			//存放String p0,String p1
			String parameterTypeContent = "";
			//存放p0,p1
			String parameterContent = "";
			int parameters = 0;
			for (Class<?> parameterType : parameterTypes) {
				//参数类名 String
				String temp = parameterType.getSimpleName();
				//String p0,String p1,
				parameterTypeContent += temp+" p"+parameters+",";
				//p0,p1,
				parameterContent += "p"+parameters+",";
				parameters++;
			}
			//截取最后一位
			if(parameters>0){
				parameterTypeContent = parameterTypeContent.substring(0,parameterTypeContent.length()-1);
				parameterContent = parameterContent.substring(0,parameterContent.length()-1);
			}
			//获取参数名称 public String query(String name) {
			methodContent.append(tab+"public "+returnType+" "+methodName+"("+parameterTypeContent+"){"+line);
			//System.out.println("打印日志");
			methodContent.append(tab+tab+"System.out.println(\"打印日志\");"+line);
			//target.query(name);
			methodContent.append(tab+tab+"target."+methodName+"("+parameterContent+");"+line);
			//return null;
			methodContent.append(tab+tab+"return null;"+line);
			methodContent.append(tab+"}"+line);
		}
		content = packageContent+importContent+clazzFirstLineContent+filedContent+constructorContent+methodContent+"}";

		
		try {
			String packageStr = DynamicProxy.class.getPackage().getName().replace(".","/");
//			File file = new File("d:\\com\\$Proxy.java");
			File file = new File("d:\\com\\xiaobi\\proxy\\$Proxy.java");
//			File file = new File(System.getProperty("user.dir")+"/spring-xb-test/src/main/java/"+packageStr+"/$Proxy.java");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();

			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

			StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
			Iterable units = fileMgr.getJavaFileObjects(file);

			JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
			t.call();

			//Class.forName()
			//fileMgr.close();

			URL[] urls = new URL[]{new URL("file:D:\\\\")};
			URLClassLoader urlClassLoader = new URLClassLoader(urls);
			//File file = new File("d:\\com\\xiaobi\\proxy\\$Proxy.java");
			//String packageContent = "package "+packageName+";"+line; ==> package com.xiaobi.proxy;
			//如果File file = new File("d:\\com\\$Proxy.java"); 会报 java.lang.ClassNotFoundException: com.xiaobi.proxy.$Proxy
			//如果Class cls = urlClassLoader.loadClass("com.$Proxy");会报 Exception in thread "main" java.lang.NoClassDefFoundError: com/$Proxy (wrong name: com/xiaobi/proxy/$Proxy)
			//所以这里的com.xiaobi.proxy.$Proxy要和导入类的包路径一致，并且也要和生成的文件路径一致
			Class cls = urlClassLoader.loadClass("com.xiaobi.proxy.$Proxy");
			Constructor constructor = cls.getConstructor(clazz);
			Object o  = constructor.newInstance(target);

			return  o;
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}
}
