package queue;

public class AccountQueueImpl implements QueueInterface
{
	private int increment=0;
	private Account[] accounts;
	
	public AccountQueueImpl() 
	{
		this(10);
	}
	public AccountQueueImpl(int size)
	{
		accounts = new Account[size];
	}
	public Object first() 
	{
		if(increment>0)
		{
			Account account = accounts[0];
			for(int i=0;i<increment;i++)
			{
				accounts[i]=accounts[i+1];
			}
			increment--;
			return account;
		}
		else
			return "nothing";
	}
	public void add(Object obj) 
	{
		if(!(obj instanceof Account))
		{
			System.out.println("Not Account");
			return;
		}
		accounts[increment++] = (Account)obj;
	}

	public int size() 
	{
		if(increment<0)
			increment=0;
		return increment;
	}

}
