package staticex;

public class TestCount {

	public static void main(String[] args) {
		System.out.println(Count.counter);
		System.out.println(Count.getCounter());
		Count c = new Count();
		System.out.println(c.counter);
		
		
	}

}
