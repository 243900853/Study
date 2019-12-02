package com.xiaobi.beanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

//说明在学习AOP2的时候resolveBeforeInstantiation方法里面mbd.isSynthetic的这个知识点,判断bd是否是合成类
public class SyntheticTest {
	public void test(){
		SyntheticTestInner inner = new SyntheticTestInner();
		//Java私有变量是不允许外部外部类访问的
		//调用内部类的私有变量其实是调用里面的一个方法,这个方法是Java自己生成的（反编译是看不到,javap才可以看到）,供外部类访问
		//inner.i ==> inner.access$000  这种就是合成方法
		System.out.println(inner.i);
	}

	class SyntheticTestInner{
		//合成方法
		private int i;
		//合成类
		private SyntheticTestInner(){}
	}

	public static void main(String[] args) {
		System.out.println("test");
		//合成方法证明
		for (Method declaredMethod : SyntheticTestInner.class.getDeclaredMethods()) {
			//Java自己生成的一个方法供外部使用类私有变量  access$000()
			System.out.println("内部类的合成方法："+declaredMethod.getName());
		}
		//合成类证明
		//Java会自动生成一个合成类SyntheticTest$1
		// 同时生成一个public类型的构造方法,方法名同自定义SyntheticTestInner()构造方法一样
		//参数1 SyntheticTest 参数2 SyntheticTest$1
		int i = 0;
		int j = 0;
		for (Constructor<?> declaredConstructor : SyntheticTestInner.class.getDeclaredConstructors()) {
			System.out.println("=============第"+i+"个构造方法=============");
			j=0;
			for (Parameter parameter : declaredConstructor.getParameters()) {
				System.out.println("第"+j+"个参数："+parameter.getType());
				j++;
			}
			i++;
		}
	}
}
