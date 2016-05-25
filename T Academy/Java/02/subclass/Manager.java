package subclass;

import java.util.Date;

public class Manager extends Employee {
	
	public Manager(String name)
	{
		super(name);
	}
	public Manager(String name, double salary, String department)
	{
		super(name, salary);
		this.department=department;
	}
	
	int i = 20;
	public String department;
	
	
	public String getDetail()
	{
		return super.getDetail()
				+ ",Department: " +department + i;
	}

}
