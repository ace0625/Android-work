package shipping;

public class ShippingMain {

	public static void main(String[] args) {
		Company c = Company.getCompany();
		
		c.addVehicle(new Truck(100.0, 10.0));
		c.addVehicle(new RiverBarge(200.0, 10.0));
		c.addVehicle(new RiverBarge(200.0, 10.0));
		c.addVehicle(new Truck(100.0, 10.0));
		c.addVehicle(new Truck(100.0, 10.0));
		
		FuelNeedsReport report = new FuelNeedsReport();
		report.generateText(System.out);
	}

}
