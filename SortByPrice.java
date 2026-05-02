
// Shahd Dar Ass
// ID : 1230652
// section : 5L
import java.util.Comparator;

public class SortByPrice implements Comparator<StockItem> {

	@Override
	public int compare(StockItem o1, StockItem o2) {
		return Double.compare(o2.getPrice(), o1.getPrice());
	}

}
