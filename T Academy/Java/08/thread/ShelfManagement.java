package thread;

import java.util.ArrayList;

class Shelf
{
	ArrayList<String> books = new ArrayList<String>();
	
	public Shelf()
	{
		books.add("test1 ");
		books.add("test2 ");
		books.add("test3 ");
	}
	public synchronized String lendBook() throws InterruptedException
	{
		while(books.size() <= 0) //return null;
		{
			Thread t = Thread.currentThread();
			System.out.println(t.getName() + "waiting start");
			wait();
			System.out.println(t.getName() + "waiting end");
		}
			
		String name = books.remove(0);
		System.out.println(name + "check out");
		return name;
	}
	public synchronized void returnBook(String name)
	{
		books.add(name);
		System.out.println(name + "Return");
		notifyAll();
	}
	
	public void readBook() throws InterruptedException
	{
		Thread.sleep(5000);
	}
}

class Student extends Thread
{
	public void run()
	{
		try 
		{
			String name = ShelfManagement.shelf.lendBook();
			if (name == null) return;
			ShelfManagement.shelf.readBook();
			ShelfManagement.shelf.returnBook(name);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}
}

public class ShelfManagement {
	public static Shelf shelf = new Shelf();
	
	public static void main(String[] args) 
	{
		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();
		Student student4 = new Student();
		Student student5 = new Student();
		Student student6 = new Student();
		
		student1.start();
		student2.start();
		student3.start();
		student4.start();
		student5.start();
		student6.start();
		
	}

}
