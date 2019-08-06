
public class Engine {

	public void trade(String fix35, String side, double qty) {
		
		Book book =new Book();			
		double bidBook[][]=book.getBidBook();
		double askBook[][]=book.getAskBook();
				
		if (fix35!="D") {
			System.out.println("Not supported by this engine"+fix35);
			System.exit(0);
		}
		if(side=="BUY") {
			sendExchangeOrder(askBook,qty);
		}
		else if(side == "SELL") {
			sendExchangeOrder(bidBook,qty);
		}
	}
	
	public void sendExchangeOrder(double book[][], double qty) {
		
		Exchange exchange=new Exchange();
		
		int i=0,slice=1;
		double fillQty=0;
		System.out.println("Execution log:");
		while(qty>=1000000) {
			if(book[i][1]>=5000000) {
				fillQty=5000000;
			}
			else if(book[i][1]>=3000000) {
				fillQty=3000000;
			}
			else if(book[i][1]>=1000000) {
				fillQty=1000000;
			}
			else {
				System.out.println("Remaining quantity can not be filled "
						+ "because this algorithm fills in increments of 1,3 & 5 million");
				break;
			}
			book[i][1]=exchange.fill(fillQty,book[i][1]);
			
			qty=qty-fillQty;
			if(book[i][1]<1000000)
				i++;	
			System.out.println("Order Slice: "+ slice++);
			System.out.println("Qty filled: "+fillQty);
			System.out.println("Qty remaining: "+qty);
		}
		if(qty==0)
			System.out.println("Order Executed Successfully");	
		else 
			System.out.println("Partial Fill");
	}

}
