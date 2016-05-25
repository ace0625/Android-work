public class RemoteData 
{
	public String name = "이름없음";
	public String addr = "주소없음";
	
	public RemoteData(){}
	public RemoteData(String name, String addr)
	{
		this.name=name;
		this.addr=addr;
	}
	
	public void sayHello()
	{
		System.out.println(this.name + "안녕하세요!!");
	}
	public void sayHello(String name)
	{
		this.name=name;
		System.out.println(this.name + "안녕하세요!!");
	}
	public void sayGoodbye()
	{
		System.out.println(this.name + "안녕히가세요!");
	}
	public String toString()
	{
		return super.toString() + ":" + name + ":" + addr;
	}
}
