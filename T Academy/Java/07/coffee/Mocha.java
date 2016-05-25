package coffee;

public class Mocha extends Coffee
{
	Coffee coffee;
	public Mocha(Coffee coffee)
	{
		this.coffee=coffee;
	}
	public String brewing() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append(coffee.brewing());
		sb.append("  ");
		sb.append("With Mocha Syrup");
		return sb.toString();
	}
	
}
