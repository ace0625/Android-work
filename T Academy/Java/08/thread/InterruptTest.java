package thread;

public class InterruptTest extends Thread
{
	public void run()
	{
		for(int i=0; i<100; i++)
		{
			System.out.println(i+1 + " Before sleep");
		}
	try
	{
		sleep(10000);
	}
	catch (InterruptedException e)
	{
		System.out.println(e);
	}
	}
	public static void main(String[] args)
	{
		InterruptTest test = new InterruptTest();
		test.start();
		test.interrupt();
	}
}
