package shipping;

public abstract class Vehicle 
{
	public abstract double calcFuelEfficiency();
	public abstract double calcTripDistance();

	public final double calFuelNeeds()
	{
		return calcTripDistance()/calcFuelEfficiency();
	}
	
	

}


Statement