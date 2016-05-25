package shipping;

import java.io.PrintStream;

public class FuelNeedsReport
{
	public void generateText(PrintStream output)
	{
		Company c = Company.getCompany();
		Vehicle vc[];
		vc =c.getVehicle();
		double result = 0;
		for(int i=0; i<vc.length; i++)
		{
				
			result += vc[i].calFuelNeeds();
		}
		output.println("Total fuel needs is " +result);
	}
}
