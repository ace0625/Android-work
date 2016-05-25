package subclass;

import java.util.Date;

public class Employee {
	
	int i = 10;
	public String name;
	public double salary;
	public Date birthDate;
	
	

	public Employee(String name, double salary, Date dob)
	{
		this.name=name;
		this.salary=salary;
		this.birthDate=dob;
	}
	public Employee(String name, double salary)
	{
		this(name, salary, null);
	}
	public Employee(String name, Date dob)
	{
		this(name, 10000.0, dob);
	}
	public Employee(String name)
	{
		this(name,10000.0);
	}
	
	public String getDetail()
	{
		return "Name: " +name+ ",Salary: " +salary ;
	}
}
