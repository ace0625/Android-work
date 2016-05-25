package first;

import junit.framework.TestCase;

public class StudentTest extends TestCase
{
	public void testCreateStudent()
	{
		Student s=new Student();
		s.setName("James");
		assertEquals(s.getName(), "James");
	}

}
