package stack;

public class AccountStackImpl implements StackInterface
{
	private int increment=0;
	private Account[] accounts;
	
	public AccountStackImpl()
	{
		this(10);
	}
	public AccountStackImpl(int size)
	{
		accounts=new Account[size];
	}
	public void push(Object obj) 
	{
		if(!(obj instanceof Account))
		{
			System.out.println("Not account");
			return;
		}
		accounts[increment++] = (Account)obj;
	}
	public Object pop() 
	{
		
		Account account = accounts[++increment];
		accounts[increment] = null;
		
		if(increment<0)
			increment=0;
		
		return account;
	}

	public int size() 
	{
		return increment;
	}

}
