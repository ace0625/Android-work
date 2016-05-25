package stack;

public class AccountStackTest {

	public static void main(String[] args) {
		AccountStackImpl impl = new AccountStackImpl();
		
		impl.push(new Account(15000, "1234 1", "test1"));
		impl.push(new Account(25000, "1234 2", "test2"));
		impl.push(new Account(35000, "1234 3", "test3"));
		
		System.out.println(impl.pop());
		System.out.println(impl.pop());
		System.out.println(impl.pop());
		
		
	}

}
