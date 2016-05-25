package queue;

public class AccountQueueTest {
	public static void main(String[] args) {
		
		AccountQueueImpl impl = new AccountQueueImpl();
		impl.add(new Account(15000, "1234_1", "test1"));
		impl.add(new Account(35000, "1234_2", "test2"));
		impl.add(new Account(25000, "1234_3", "test3"));
		
		System.out.println(impl.first());
		System.out.println(impl.first());
		System.out.println(impl.first());
	}

}
