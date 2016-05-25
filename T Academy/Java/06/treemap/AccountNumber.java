package treemap;

import java.util.Comparator;

public class AccountNumber implements Comparator
{
	String accountNumber;
	AccountNumber(){}
	AccountNumber(String s)
	{
		accountNumber = s;
	}
	public String getAccountNumber()
	{
		return accountNumber;
	}
	public int compare(Object o1, Object o2) 
	{
		String tempAccountNumber1 = (String)((AccountNumber)o1).getAccountNumber();
		String tempAccountNumber2 = (String)((AccountNumber)o2).getAccountNumber();
		
		return tempAccountNumber1.compareTo(tempAccountNumber2);
	}

}
