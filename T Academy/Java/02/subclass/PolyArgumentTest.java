package subclass;


class Product
{
	int price;
	int bonusPoint;
	
	Product(int price)
	{
		this.price = price;
		this.bonusPoint = (int)(price/10.0);
	}
}

class TV extends Product
{
	TV()
	{
		super(100);
	}
	public String toString()
	{
		return "TV";
	}
}

class Computer extends Product
{
	Computer()
	{
		super(200);
	}
	public String toString()
	{
		return "Computer";
	}
}

class Buyer
{
	int money = 1000;
	int bonusPoint = 0;
	
	void buy(Product p)
	{
		if(money < p.price)
		{
			System.out.println("not enough money");
			return;
		}
		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p+ "를 구입했습니다.");
	}
}
public class PolyArgumentTest {
	 
	public static void main(String[] args) {
		Buyer b=new Buyer();
		b.buy(new Computer());
		b.buy(new TV());
		
		System.out.println(b.money);
		System.out.println(b.bonusPoint);

	}

}
