package subclass;

class Shape
{
	public void draw()
	{
		System.out.println("Draw Shape");
	}
}

class Circle extends Shape
{
	public void draw()
	{
		System.out.println("Draw circle");
	}
}

class Triangle extends Shape
{
	public void draw()
	{
		System.out.println("Draw triangle");
	}
}
class Rectangle extends Shape
{
	public void draw()
	{
		System.out.println("Draw rectangle");
	}
}

public class ShapeTest {
	
	public static void main(String[] args)
	{
		Shape s=new Shape();
		
		if(args[0].equals("circle"))
		{
			s=new Circle();
			
			
		}
		else if(args[0].equals("triangle"))
		{
			s=new Triangle();
			
		}
		else if(args[0].equals("Rectangle"))
		{
			s=new Rectangle();
				
		}
		s.draw();
		s.draw();	
			
	}
}
