package object;

public class Mydate 
{
	private int day;
	private int month;
	private int year;
	
	public Mydate(int day, int month, int year)
	{
		this.day=day;
		this.month=month;
		this.year=year;
	}

	public boolean equals (Mydate d) 
	{
		
		boolean result = false;
		
		if(d!=null)
		{
			if ((day==d.day) && (month==d.month) && (year==d.year))
				result = true;
		}
		
		return result;
	}

	@Override
	public int hashCode() {
		
		return (new Integer(day).hashCode()
				^ new Integer(month).hashCode()
				^ new Integer(year).hashCode());
	}
	
}
