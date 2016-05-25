package treemap;

public class AccountTreeMapTest {

	public static void main(String[] args) {
		AccountManagementTreemap manager = new AccountManagementTreemap();
		
		manager.insertAccount(15000, "1234_1", "Tomas");
		manager.insertAccount(55000, "1234_3", "James");
		manager.insertAccount(25000, "1234_2", "Kim");
		manager.insertAccount(85000, "1234_5", "king");
		manager.insertAccount(75000, "1234_4", "Tos");
		
	
		manager.displayAll();
		System.out.println("=============================");
		manager.setDeposit(5000, "1234_1");
		manager.setDeposit(5000, "1234_3");
		manager.setDeposit(5000, "1234_4");
		manager.displayAll();
	}

}
