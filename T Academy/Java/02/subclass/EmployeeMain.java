package subclass;

public class EmployeeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee e=new Employee("james");
		System.out.println(e.getDetail());
		
		Manager m=new Manager("Tomans,s " +
				"");
		System.out.println(m.getDetail());
		
		Employee e2=new Manager("ss");
		System.out.println(e2.getDetail());
		System.out.println(e2.i);
		
		Object
	}

}
