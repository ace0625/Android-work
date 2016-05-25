import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicLoader 
{
	public static Class loadClass(String url, String className) throws MalformedURLException, ClassNotFoundException
	{
		URL[] urlArray = {new URL(url)};
		URLClassLoader cLoader = new URLClassLoader(urlArray);
		Class c = cLoader.loadClass(className);
		return c;
	}
	
}
