package object;

import java.util.Date;

class Dog
{
	String type;
	
	Dog(String type)
	{
		this.type=type;
	}

	@Override
	public String toString() {
		return type;
	}
}

public class ToStringTest {

	public static void main(String[] args) 
	{
		Dog dog=new Dog("Áøµ¾°³");
		
		System.out.println(dog);
		
		String s = new String("test");
		System.out.println(s);
		
		Date d = new Date();
		System.out.println(d);

	}

}
