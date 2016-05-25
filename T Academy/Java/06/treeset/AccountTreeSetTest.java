package treeset;

public class AccountTreeSetTest {
	public static void main(String[] args) {
		AccountManagementTreeSet manager = new AccountManagementTreeSet();
		
		manager.insertAccount(15000, "1234_1", "Tomas");
		manager.insertAccount(55000, "1234_2", "James");
		manager.insertAccount(25000, "1234_3", "Kim");
		manager.insertAccount(85000, "1234_4", "king");
		manager.insertAccount(75000, "1234_5", "Tos");
		
		manager.displayAll();
	}

}
