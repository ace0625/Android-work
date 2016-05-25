package staticex;

public class Count 
{
	public static int counter = 0;
	public int serialNumber;	
	
	public static final String NEWLINE = "\n";
	
	public Count()
	{
		counter++;
		serialNumber = counter;
	}
	public static int getCounter()
	{
		int i = 0;
		return counter;
	}
	
	public void print()
	{
		System.out.println("aaaaaa" + Count.NEWLINE + "bbbbbb");
		
	}
}
