package subclass;

class Servant
{
	public void cleaningHome()
	{
		System.out.println("청소합니다");
	}
	public void washCar()
	{
		System.out.println("세차 합니다");
	}
}

class Manager2
{
	public void action(Object obj)
	{
		if(obj instanceof Servant)
		{
			Servant s=(Servant)obj;
			s.cleaningHome();
			s.washCar();
		}
		else
		{
			System.out.println("you are not servant");
		}
			
	}
}

public class ServantMain {

	public static void main(String[] args) {
		Manager2 m = new Manager2();
		m.action(new Object());
		m.action(new Servant());
	}

}
