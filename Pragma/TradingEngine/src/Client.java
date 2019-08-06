
public class Client {
	
	static String receiveResponse="";
	String fix35, side;
	double OrdQty;
	
	Client(String fix35, String side, double qty) {
		this.fix35=fix35;
		this.side=side;
		OrdQty=qty;
	}
	
	public void sendOrder() {
		Engine.trade(fix35,side,OrdQty);
	}
	
	public static void main( String args[] ){
		
		Client client_1=new Client("D","BUY",20000000);
		client_1.sendOrder();
		System.out.println("Response Received: "+receiveResponse);
		
		Client client_2=new Client("D","SELL",12000000);
		client_2.sendOrder();
		System.out.println("Response Received: "+receiveResponse);
		
	}

}
