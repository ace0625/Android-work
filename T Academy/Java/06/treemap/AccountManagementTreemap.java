package treemap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


public class AccountManagementTreemap 
{
	private TreeMap<AccountNumber, Account> accounts;
	
	public AccountManagementTreemap()
	{
		accounts = new TreeMap<AccountNumber, Account>(new AccountNumber());
	}
	public void insertAccount(float money, String accountNumber, String accountName)
	{
		
		accounts.put(new AccountNumber(accountNumber), new Account(money, accountNumber, accountName));
	}
	public void setDeposit(float money, String accountNumber)
	{	
		Account account = accounts.get(new AccountNumber(accountNumber));
		if(account==null)
			System.out.println("no account");
		
		account.deposit(money);
		
	}
	public void setWithdraw(float money, String accountNumber)
	{
		Account account = accounts.get(new AccountNumber(accountNumber));
		if(account==null)
		{
			System.out.println("no account");
			return;
		}
		account.withdraw(money);
			
	}
	public void displayAll()
	{
		Set keySet = accounts.keySet();
		Iterator ir = keySet.iterator();
		while(ir.hasNext())
		{
			AccountNumber accountNumber = (AccountNumber)ir.next();
			Account account = accounts.get(accountNumber);
			System.out.println(account);
			
		}
	}
	
}
