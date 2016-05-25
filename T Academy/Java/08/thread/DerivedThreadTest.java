package thread;


class DerivedThread extends Thread
{
	public void run()
	{
		for(int i=0; i<200; i++)
		{
			System.out.print(i + "\t");
		}
	}
	
}

class Command implements Runnable
{
	public void run() 
	{
		for(int i=0; i<200; i++)
		{
			System.out.print(i + "\t");
		}
	}
	
}
public class DerivedThreadTest {
	public static void main(String[] args) 
	{
		System.out.println("Main Start");
	//	DerivedThread d1 = new DerivedThread();
	//	DerivedThread d2 = new DerivedThread();
		
		Command c = new Command();
		Thread d1 = new Thread(c);
		Thread d2 = new Thread(c);
		
		
		d1.start();
		d2.start();
		System.out.println("Main End");
	}

}
