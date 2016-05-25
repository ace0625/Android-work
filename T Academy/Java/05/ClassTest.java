import java.lang.reflect.Field;
import java.lang.reflect.Method;

class A
{
	public void m()
	{
		System.out.println("A()");
	}
}

class B extends A
{
	public B()
	{
		System.out.println("B()");
	}
	public void m()
	{
		System.out.println("B.m()");
	}
	
}

public class ClassTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		A a = new B();
		Class c1 = a.getClass();
		System.out.println(c1.getName());
		
		Object obj = c1.newInstance();
		System.out.println(obj);
		
		
		Class c2= Class.forName("B");
		((A)c2.newInstance()).m();
		
		//String s = new String();
		//System.out.println(s.getClass().getName());
		
		String name = "java.lang.String";
		Class c3 = Class.class.forName(name);
		Object obj2 = c3.newInstance();
		String str = (String)obj2;
	
		String str1 = new String();
	/*	Class c = Integer.class;
		
		Field[] f = c.getFields();
		for(int i=0; i<f.length; i++)
		{
			System.out.println(f[i]);
		}
		System.out.println("\n");
		
		Method[] m = c.getMethods();
		for(int i=0; i<f.length; i++)
		{
			System.out.println(f[i]);
		}
		
	*/
	}

}
