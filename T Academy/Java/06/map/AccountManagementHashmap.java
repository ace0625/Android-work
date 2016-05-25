package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AccountManagementHashmap 
{
	private HashMap<String, Account> accounts;
	
	public AccountManagementHashmap() 
	{
		accounts = new HashMap<String, Account>();
	}
	public void insertAccount(float money, String accountNumber, String accountName)
	{
		accounts.put(accountNumber, new Account(money, accountNumber, accountName));
	}
	public void setDeposit(float money, String accoutNumber)
	{
		Account account = accounts.get(accoutNumber);
		if(account==null)
			System.out.println("no account");
		
		account.deposit(money);
		
	}
	public void setWithdraw(float money, String accountNumber)
	{
		Account account = accounts.get(accountNumber);
		if(account==null)
		{
			System.out.println("no account");
			return;
		}
		account.withdraw(money);
			
	}
	public void displayAll()
	{
		// Collection values = accounts.values();
		
		Set keySet = accounts.keySet();
		Iterator ir = keySet.iterator();
		while(ir.hasNext())
		{
			String accountNumber = (String)ir.next();
			Account account = accounts.get(accountNumber);
			System.out.println(account);
			
		}
	}
}
