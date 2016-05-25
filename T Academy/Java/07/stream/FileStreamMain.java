package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamMain {

	public static void main(String[] args) throws IOException 
	{
		FileOutputStream fos = new FileOutputStream("a.dat", true);	//true-append, false- overwrite
		fos.write(72);
		fos.write(101);
		fos.write(108);
		fos.write(108);
		fos.write(111);
		
		fos.close();
		
		FileInputStream fis = new FileInputStream("a.dat");
		
		int i;
		while((i=fis.read()) != -1)
		{
			System.out.print((char)i);
		}
		fis.close();
	}

}
