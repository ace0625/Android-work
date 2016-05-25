package stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreamTest {
	public static void main(String[] args) throws IOException 
	{
		FileOutputStream fos = new FileOutputStream("data.dat");
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.write(100);
		dos.writeInt(100);
		dos.writeFloat(3.14f);
		dos.writeChar('A');
		dos.close();
		
		FileInputStream fis = new FileInputStream("data.dat");
		DataInputStream dis = new DataInputStream(fis);
		
		int i = dis.read();
		int i2 = dis.readInt();
		float f = dis.readFloat();
		char c = dis.readChar();
		
		dis.close();
		
		System.out.println(i + "," + i2 + "," + f + "," + c);
	}

}
