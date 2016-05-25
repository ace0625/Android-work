package arraylist;

public class AccountArrayListTest {

	public static void main(String[] args) {
		AccountManagementArrayList manager = new AccountManagementArrayList();
		manager.insertAccount(15000, "1234_1", "James" );
		manager.insertAccount(25000, "1234_2", "Kim" );
		manager.insertAccount(35000, "1234_3", "Dan" );
		manager.insertAccount(45000, "1234_4", "Rick" );
		manager.insertAccount(55000, "1234_5", "Nick" );
		
		manager.displayAll();
		
		manager.setDeposit(5000, "1234_1");
		manager.setDeposit(5000, "1234_2");
		manager.setDeposit(5000, "1234_3");
		manager.setDeposit(5000, "1234_3");
		manager.setDeposit(5000, "1234_3");
		System.out.println("=================================");
		
		manager.displayAll();
	}

}
