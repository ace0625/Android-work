package map;

public class AccountHashMapTest {

	public static void main(String[] args) {
		AccountManagementHashmap manager = new AccountManagementHashmap();
		
		manager.insertAccount(15000, "1234_1", "James1");
		manager.insertAccount(35000, "1234_2", "James2");
		manager.insertAccount(55000, "1234_3", "James3");
		manager.insertAccount(25000, "1234_4", "James4");
		manager.insertAccount(95000, "1234_5", "James5");
		
	
		manager.displayAll();
		System.out.println("=====================================");
		
		manager.setDeposit(5000, "1234_1");
		manager.setDeposit(5000, "1234_2");
		manager.displayAll();
	}

}
