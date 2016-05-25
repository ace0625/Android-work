package thread;

public class JoinTest extends Thread {

	private int start, end;
	public int sum;
	
	public JoinTest(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	public void run()
	{
		for(int i=start; i<=end; i++)
		{
			sum += i;
		}
	}
	public static void main(String[] args) throws InterruptedException 
	{
		JoinTest jt1 = new JoinTest(1, 50);
		JoinTest jt2 = new JoinTest(51, 100);
		
		jt1.start();
		jt2.start();
		
		jt1.join();
		jt2.join();
		
		int total = jt1.sum + jt2.sum;
		
		System.out.println(total + "," + jt1.sum + "," + jt2.sum);
		
	}

}
