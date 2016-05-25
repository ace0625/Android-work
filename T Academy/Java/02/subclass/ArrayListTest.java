package subclass;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList<Object> list = new ArrayList<Object>();
		
		list.add(new Integer(100));
		list.add(new String("test"));
		
		for(int i=0; i<list.size(); i++)
		{
			Object obj = list.get(i);
			int num;
			String str;
			
			if(obj instanceof Integer)
			{
				num = (Integer)obj;
				System.out.println(num);
			}
			
			else if(obj instanceof String)
			{	
				str = (String)obj;
				System.out.println(str);
			}
			
		}
	
		
	}

}
