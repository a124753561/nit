/**
 *====================================================
 * 文件名称: ReflectTest.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月19日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package nit.test;

import java.lang.reflect.Constructor;

/**
 * @ClassName: ReflectTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月19日 下午5:43:40
 */
public class ReflectTest {

	@SuppressWarnings("unchecked")
	public static Car initDefaultCar() throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("nit.test.Car");

		Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
		Car car = (Car) cons.newInstance();
		
		return car;

	}

}
