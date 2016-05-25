import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class DynamicDataMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		System.out.println("downloading....");
		URL url = new URL("http://127.0.0.1:9090/DynamicData.class");
		InputStream in = url.openStream();
		
		FileOutputStream fos = new FileOutputStream(".//bin//DynamicData.class");
		int i;
		while((i = in.read()) != -1)
		{
			fos.write(i);
			System.out.print("|");
		}
		in.close();
		fos.close();
		System.out.println("complete");
		
		Class c = Class.forName("DynamicData");
		Object obj = c.newInstance();
		System.out.println(obj);
		
		DynamicData data = (DynamicData)obj;
		data.sayHello();
	}

}
