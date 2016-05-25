package set;

public class AccountHashSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccountManagementHashSet manager = new AccountManagementHashSet();
		
		manager.insertAccount(15000, "12341", "James1");
		manager.insertAccount(25000, "12342", "James2");
		manager.insertAccount(35000, "12343", "James3");
		manager.insertAccount(35000, "12341", "James3");
		
		manager.displayAll();
		
		manager.setDeposit(5000, "12341");
		manager.setDeposit(5000, "12342");
		System.out.println("====================================");
		manager.displayAll();
	}

}
