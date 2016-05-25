package interfaceex;

public class VideoShopMain {

	public static void main(String[] args) {
		
		IQueue shop = new VideoShop();
		shop.enQueue("Matrix1");
		shop.enQueue("Matrix2");
		shop.enQueue("Matrix3");
		
		System.out.println(shop.deQueue());
		System.out.println(shop.deQueue());
		System.out.println(shop.deQueue());
		
	}	

}
