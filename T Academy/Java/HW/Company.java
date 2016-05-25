package shipping;

import java.util.ArrayList;


public class Company
{
	private static Company instance = new Company();
	private Vehicle vc[] = new Vehicle[5];
	private static int count =0;
	private Company(){}
	
	public static Company getCompany()
	{
		if(instance == null)
			instance = new Company();
		return instance;
	}
	public void addVehicle(Vehicle v)
	{
		int i;
		i=count;
		this.vc[i] =v;
		count++;
	}
	public Vehicle[] getVehicle()
	{
		return vc;
	}
}
