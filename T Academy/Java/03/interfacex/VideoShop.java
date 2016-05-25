package interfaceex;

public class VideoShop extends Shop implements IQueue 
{
	
	public void enQueue(String video) {
		shelf.add(video);
	}

	public String deQueue() {
		return shelf.remove(0);
	}
	
}
