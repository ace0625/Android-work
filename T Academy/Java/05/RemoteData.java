public class RemoteData 
{
	public String name = "�̸�����";
	public String addr = "�ּҾ���";
	
	public RemoteData(){}
	public RemoteData(String name, String addr)
	{
		this.name=name;
		this.addr=addr;
	}
	
	public void sayHello()
	{
		System.out.println(this.name + "�ȳ��ϼ���!!");
	}
	public void sayHello(String name)
	{
		this.name=name;
		System.out.println(this.name + "�ȳ��ϼ���!!");
	}
	public void sayGoodbye()
	{
		System.out.println(this.name + "�ȳ���������!");
	}
	public String toString()
	{
		return super.toString() + ":" + name + ":" + addr;
	}
}
