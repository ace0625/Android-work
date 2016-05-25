package template;

public class CharacterDisplay extends AbstractDisplay {
	char ch;
	public CharacterDisplay (char ch)
	{
		this.ch=ch;
	}
	@Override
	public void open() {
		System.out.print("<<");
		
	}
	@Override
	public void print() {
		System.out.print(ch);
		
	}
	@Override
	public void close() {
		System.out.print(">>");
	}
	
	public void line()
	{
		System.out.println("\n==============");
	}
	
}
