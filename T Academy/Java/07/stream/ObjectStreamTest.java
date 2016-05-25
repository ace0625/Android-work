package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person implements Serializable
{
	public String name;
	public int age;
	
	public Person(String name, int age)
	{
		this.name=name;
		this.age=age;
	}
	public String toString()
	{
		return name + "," +age;
	}
}
public class ObjectStreamTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		FileOutputStream fos = new FileOutputStream("Object.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person p1 = new Person("ÀÌ½Â±â", 30);
		Person p2 = new Person("±èµ¿·ü", 40);
		
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.close();
		
		FileInputStream fis = new FileInputStream("Object.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person ps1 = (Person)ois.readObject();
		Person ps2 = (Person)ois.readObject();
		System.out.println(ps1);
		System.out.println(ps2);
		ois.close();
	}
	

}
