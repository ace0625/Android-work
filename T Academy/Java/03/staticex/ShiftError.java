package staticex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ShiftError 
{
	public URL makeURL(String urlStr) throws FileNotFoundException, MalformedURLException
	{
		FileReader fr = new FileReader("a.dat");
		return new URL(urlStr);
	}
		
	
	public static void main(String[] args)
	{
		ShiftError sh = new ShiftError();
		try 
		{
			sh.makeURL("http://www.naver.com");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace(); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
