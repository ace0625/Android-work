package set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AccountManagementHashSet {
	private Set<Account> accounts;
	
	public AccountManagementHashSet()
	{
		this(10);
	}
	public AccountManagementHashSet(int size)
	{
		accounts = new HashSet<Account>(size);
	}
	public void insertAccount(float money, String accountNumber, String accountName)
	{
		accounts.add(new Account(money, accountNumber, accountName));
		
	}
	public void setDeposit(float money, String accountNumber)
	{
		/*for(int i=0; i<accounts.size(); i++)
		{
			Account account = accounts.get(i);
			String tempNumber = account.getAccountNumber();
		
			if(tempNumber.equals(accountNumber))
			{
				account.deposit(money);
				return;
			}
			
		}
		
		System.out.println("no account");
		*/
		
		Iterator<Account> ir = accounts.iterator(); 
		while(ir.hasNext())
		{
			Account account = ir.next();
			String tempNumber = account.getAccountNumber();
			if(tempNumber.equals(accountNumber))
			{
				account.deposit(money);
			}
		}
	}
	public void setWithdraw(float money, String accountNumber)
	{
		/* for(int i=0; i<accounts.size(); i++)
		{
			Account account = accounts.get(i);
			String tempNumber = account.getAccountNumber();
			if(tempNumber.equals(accountNumber))
			{
				account.withdraw(money);
				return;
			}
			
		}
		System.out.println("no account");
		*/
		
		Iterator<Account> ir = accounts.iterator();
		while(ir.hasNext())
		{
			Account account = ir.next();
			String tempNumber = account.getAccountNumber();
			if(tempNumber.equals(accountNumber))
			{
				account.withdraw(money);
			}
		}
	}
	public void displayAll()
	{
	/*	for(int i=0; i<accounts.size();i++)
		{
			System.out.println(accounts.get(i));
		}*/
		
		Iterator<Account> ir = accounts.iterator();
		while(ir.hasNext())
		{
			Account account = ir.next();
			System.out.println(account);
		}
	}
}
