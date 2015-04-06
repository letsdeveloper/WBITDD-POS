package pos;

public class PointOfSale {

	private Display display;
	private ItemStore itemStore;

	public PointOfSale(Display display, ItemStore itemStore) {
		this.display = display;
		this.itemStore = itemStore;
	}

	public void onBarcode(String barcode) {
		if (isValid(barcode)) {
			if (itemStore.hasPrice(barcode)) {
				display.show(itemStore.getPrice(barcode));
			} else {
				display.show("Unknown barcode.");
			}
		} else {
			display.show("Invalid barcode.");
		}
	}

	private boolean isValid(String barcode) {
		return barcode != null && !barcode.isEmpty();
	}

}
