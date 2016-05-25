package interfaceex;

interface IFork
{
	void dig();
}
interface ITank
{
	void shoot();
}

abstract class AutoCar
{
	public abstract void drive();
}

class MyCar extends AutoCar implements IFork, ITank
{

	public void shoot() 
	{
		System.out.println("shoot");	
	}
	public void dig() 
	{
		System.out.println("dig");	
	}
	public void drive() 
	{
		System.out.println("drive");
	}
	public void soundHorn()
	{
		System.out.println("sound Horn");
	}
	
}


public class MyCarMain {

	public static void main(String[] args) 
	{
		MyCar m = new MyCar();
		m.dig();
		m.drive();
		m.shoot();
		m.soundHorn();
		
		IFork f = m;
		f.dig();
		ITank t =m;
		t.shoot();
	}

}
