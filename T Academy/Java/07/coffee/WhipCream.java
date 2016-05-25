package coffee;

public class WhipCream extends Coffee
{
	Coffee coffee;
	public WhipCream(Coffee coffee) 
	{
		this.coffee=coffee;
	}
	
		public String brewing() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append(coffee.brewing());
		sb.append("  ");
		sb.append("With whipcream");
		return sb.toString();
	}
	
}
