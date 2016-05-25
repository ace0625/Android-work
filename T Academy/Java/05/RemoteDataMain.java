import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class RemoteDataMain 
{

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException 
	{
		String url = "http://127.0.0.1:9090/";
		Class c = DynamicLoader.loadClass(url, "RemoteData");
		//Object obj=c.newInstance();
		
		//Object obj = c.newInstance();
		//System.out.println(obj);
		
		Class[] parameterTypes = new Class[]{String.class, String.class};
		Constructor cons = c.getConstructor(parameterTypes);
		
		Object[] initargs = new Object[]{"ÀÌ½Â±â", "¼­¿ï"};
		Object obj = cons.newInstance(initargs);
		//System.out.println(obj);
	
		Method m = c.getMethod("sayHello", null);
		m.invoke(obj, null);
		Method m2= c.getMethod("sayGoodbye", null);
		m2.invoke(obj, null);
		
		Class[] parameterTypes2 = new Class[]{String.class};
		Method m3 = c.getMethod("sayHello", parameterTypes2);
		Object[] initargs2 = new Object[]{"¼ÛÁß±â"};
		m3.invoke(obj, initargs2);
		
		Field f = c.getField("addr");
		f.set(obj, "´º¿å");
		Object fieldValue = f.get(obj);
		System.out.println(fieldValue);
		
	}
}


