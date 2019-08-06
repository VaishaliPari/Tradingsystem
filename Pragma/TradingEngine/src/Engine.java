
public class Engine {
	
	static String fillAck ="35=8|39=0|150=0";

	public static void trade(String fix35, String side, double qty) {
		if (fix35!="D") {
			System.out.println("Not supported by this engine"+fix35);
			System.exit(0);
		}
		if(side=="BUY") {
			int i=0,slice=1;
			double fillQty=0;
			System.out.println("Execution log:");
			while(qty>=1000000) {
				if(Book.askBook[i][1]>=5000000) {
					fillQty=5000000;
				}
				else if(Book.askBook[i][1]>=3000000) {
					fillQty=3000000;
				}
				else if(Book.askBook[i][1]>=1000000) {
					fillQty=1000000;
				}
				else {
					System.out.println("Remaining quantity can not be filled "
							+ "because this algorithm fills in increments of 1,3 & 5 million");
					break;
				}
				Exchange.askfill(fillQty,Book.askBook[i][0],i);
				qty=qty-fillQty;
				if(Book.askBook[i][1]<1000000)
					i++;	
				System.out.println("Order Slice: "+ slice++);
				System.out.println("Qty filled: "+fillQty);
				System.out.println("Qty remaining: "+qty);
			}
			if(qty==0)
				Client.receiveResponse=fillAck;
			else 
				Client.receiveResponse="Partial Fill";
		}
		else if(side == "SELL") {
			int i=Book.bidBook.length-1,slice=1;
			double fillQty=0;
			System.out.println("Execution log:");
			System.out.println("i "+i+" Book "+Book.bidBook[i][1]);
			while(qty>=1000000) {
				if(Book.bidBook[i][1]>=5000000) {
					fillQty=5000000;
				}
				else if(Book.bidBook[i][1]>=3000000) {
					fillQty=3000000;
				}
				else if(Book.bidBook[i][1]>=1000000) {
					fillQty=1000000;
				}
				else {
					System.out.println("Remaining quantity can not be filled "
							+ "because this algorithm fills in increments of 1,3 & 5 million");
					break;
				}
				Exchange.bidfill(fillQty,Book.bidBook[i][0],i);
				qty=qty-fillQty;
				if(Book.bidBook[i][1]<1000000)
					i--;
				System.out.println("Order Slice: "+ slice++);
				System.out.println("Qty filled: "+fillQty);
				System.out.println("Qty remaining: "+qty);
			}
			if(qty==0)
				Client.receiveResponse=fillAck;	
			else 
				Client.receiveResponse="Partial Fill";
		}
	}

}
