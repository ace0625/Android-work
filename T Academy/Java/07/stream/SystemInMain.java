package stream;

import java.io.IOException;
import java.io.InputStreamReader;

public class SystemInMain {

	public static void main(String[] args) throws IOException 
	{
		System.out.print("Type '³¡' when you're done ");
//		int i = System.in.read();
//		System.out.println(i);
//		i = System.in.read();
//		System.out.println(i);
		
		InputStreamReader isr= new InputStreamReader(System.in);
		
		int i;
		while((i=isr.read()) != '³¡')
		{
			System.out.print((char)i);
		}
		
	}

}
