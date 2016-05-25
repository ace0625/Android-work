package exception;

public class HelloWorld {

	public static void main(String[] args) {

		int i = 0;
		String greetings[] = {"hello", "hi", "good morning", "hahaha"};
		
		while(i<=4)
		{
			try
			{
				System.out.println(greetings[i]);
				//i++;
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println(e);
				System.out.println(e.getMessage());
				i=-1;
			}
			finally
			{
				System.out.println("here~~");
			}
			i++;
			
		}
	}

}
