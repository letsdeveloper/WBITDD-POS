package pos;

public interface ItemStore {

	String getPrice(String barcode);

	boolean hasPrice(String barcode);

}
