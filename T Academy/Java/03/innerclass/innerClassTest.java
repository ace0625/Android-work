package innerclass;


class Outer
{
	private int i = 1;
	static int si = 2;
	Inner inner;
	class Inner
	{
		int j = 3;
		
		public void print()
		{
			System.out.println(i);
			System.out.println(si);
			System.out.println(j);
			System.out.println(Outer.this.i);
		}
	}
	public Outer()
	{
		inner = new Inner();
		Inner inner2 = this.new Inner();
	}
}
public class innerClassTest {

	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.inner.print();
		
		Outer.Inner inner2 = outer.new Inner();
	}

}
