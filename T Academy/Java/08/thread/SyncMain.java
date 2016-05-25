package thread;

class Bank
{
	private int money = 10000;

	public int getMoney() 
	{
		return money;
	}

	public void setMoney(int money) 
	{
		this.money = money;
	}
	
	public void saveMoney(int save)
	{
		synchronized (this) {
			
		
		int m = this.getMoney();
		
		try 
		{
			Thread.sleep(3000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		this.setMoney(m + save);
	}
	}
	public synchronized void minusMoney(int minus)
	{
		int m = this.getMoney();
		
		try 
		{
			Thread.sleep(2000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		this.setMoney(m - minus);
	}
}

class Park extends Thread
{
	public void run()
	{
		SyncMain.myBank.saveMoney(3000);
		System.out.println("saveMoney(3000) : " + SyncMain.myBank.getMoney());
	}
}
class ParkWife extends Thread
{
	public void run()
	{
		SyncMain.myBank.minusMoney(1000);
		System.out.println("minusMoney(3000) : " + SyncMain.myBank.getMoney());
	} 
}

public class SyncMain {

	public static Bank myBank = new Bank();
	public static void main(String[] args) 
	{
		System.out.println("¿ø±Ý: " + myBank.getMoney());
		Park p = new Park();
		ParkWife pw = new ParkWife();
		
		p.start();
		try 
		{
			Thread.sleep(500);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		pw.start();
		
	}

}
