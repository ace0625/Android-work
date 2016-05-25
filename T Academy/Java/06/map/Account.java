package map;

public class Account 
{
	private float balance;
	private String accountNumber;
	private String accoutName;
	
	public Account(){}
	public Account(float balance, String accountNumber, String accountName)
	{
		this.balance = balance;
		this.accountNumber=accountNumber;
		this.accoutName= accountName;
	}
	
	public void withdraw(float amount)
	{
		if(amount>balance)
		{
			System.out.println("�ܰ����");
		}
		else
		{
			balance-=amount;
		}
	}
	public void deposit(float amount)
	{
		if(amount<=0)
		{
			System.out.println(amount+ "�δ� �ԱݺҰ�");
		}
		else
		{
			balance+=amount;
			return;
		}
	}
	public String toString()
	{
		return accoutName +" ���� ���¹�ȣ�� " + accountNumber + "�̸� �����ܰ�� " + balance +"�Դϴ�.";
	}
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccoutName() {
		return accoutName;
	}
	public void setAccoutName(String accoutName) {
		this.accoutName = accoutName;
	}
	
}
