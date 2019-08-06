
public class Exchange {
	
	public static void askfill(double SliceQty,double price,int i){
		Book.askBook[i][1]=Book.askBook[i][1]-SliceQty;	
	}
	public static void bidfill(double SliceQty,double price,int i){
		Book.bidBook[i][1]=Book.bidBook[i][1]- SliceQty;	
	}

}
