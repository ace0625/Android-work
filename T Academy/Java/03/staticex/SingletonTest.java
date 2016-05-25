package staticex;

public class SingletonTest {

	public static void main(String[] args) {
	
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2= Singleton.getInstance();
		Singleton singleton3 = Singleton.getInstance();
		
		System.out.println(singleton1 == singleton2);
		System.out.println(singleton2 == singleton3);
	}

}
