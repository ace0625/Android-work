package first;

import java.util.ArrayList;

class Cow
{
	private int weight;
	
	
	ArrayList list = new ArrayList();
	public Cow(){}
	
	public Cow(int wieght)
	{
		this.weight = weight;
	}

	public int getWeight() 
	{
		return weight;
	}

	public void setWeight(int weight) 
	{
		this.weight = weight;
	}
	public void printThis()
	{
		System.out.println(this);
		
	}
	
	
}
public class TestCow {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Cow c=new Cow();
		c.setWeight(100);
		System.out.println(c.getWeight());
		System.out.println(c);
	}

}
