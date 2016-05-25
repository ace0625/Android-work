package stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) throws IOException 
	{
		int i, len =0;
		
		FileInputStream fis = new FileInputStream(args[0]);
		FileOutputStream fos = new FileOutputStream(args[1]);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		long psecond = System.currentTimeMillis();
		while((i=bis.read()) != -1)
		{
			bos.write(i);
			len++;
		}
		fis.close();
		fos.close();
		psecond = System.currentTimeMillis() - psecond;
		
		System.out.println(len + "bytes" + "," + psecond + "seconds");
		
	}

}
