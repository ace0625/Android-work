package exception;

public class MyExceptionTest {

	public static void main(String[] args) {
		if(args[0].equals("error"))
		{
			try
			{
				throw new MyException("My error");
			}
			catch(MyException e)
			{
				System.out.println(e.getMessage());
				return;
			}
			finally
			{
				System.out.println("finally");
			}
			
		}
	}

}
