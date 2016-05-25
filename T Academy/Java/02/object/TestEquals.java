package object;

public class TestEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Mydate d1=new Mydate(23, 9, 2013);
		Mydate d2=new Mydate(23, 9, 2013);
		
		System.out.println(d1==d2);
		System.out.println(d1.equals(d2));
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
		
		
		String s1 = new String("test");
		String s2 = new String("test");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
	}

}
