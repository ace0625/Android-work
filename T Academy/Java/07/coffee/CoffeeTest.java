package coffee;

public class CoffeeTest {

	public static void main(String[] args) {
		Coffee coffee = new WhipCream(new Mocha(new Americano()));
		System.out.println(coffee.brewing());
		
		Coffee coffee2 = new Latte(new Americano());
		System.out.println(coffee2.brewing());
	}

}
