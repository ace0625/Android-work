package coffee;

public class Latte extends Coffee
{
	Coffee coffee;
	public Latte(Coffee coffee)
	{
		this.coffee=coffee;
	}
	
		public String brewing() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append(coffee.brewing());
		sb.append("  ");
		sb.append("Adding Milk");
		return sb.toString();
	}
	
}
