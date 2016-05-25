package shipping;


public class Truck extends Vehicle
{
	private double distance;
	private double efficiency;
	
	public Truck(double distance, double efficiency)
	{
		this.distance= distance;
		this.efficiency=efficiency;
	}

	public double calcFuelEfficiency() 
	{
		return efficiency;
	}

	public double calcTripDistance() 
	{
		return distance;
	}

}
