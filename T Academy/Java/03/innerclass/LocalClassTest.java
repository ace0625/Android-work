package innerclass;
	
public class LocalClassTest {
	
	String str;
	public LocalClassTest() 
	{
		this.str = str;
	}
	Runnable getRunnable(final int i)
	{
		final int j = 10;
		return new Runnable()
		//class Command implements Runnable
		{
			public void run() 
			{
				System.out.println(str);
				System.out.println(i);
				System.out.println(j);
			}
			
		};
		//return new Command();
	}
	
	Runnable runner = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	};
	
	public static void main(String[] args) 
	{
		LocalClassTest local = new LocalClassTest();
		Runnable run = local.getRunnable(100);
		run.run();
	}

}
Exception
