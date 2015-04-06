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
			onValidBarcode(barcode);
		} else {
			onInvalidBarcode();
		}
	}

	private boolean isValid(String barcode) {
		return barcode != null && !barcode.isEmpty();
	}

	private void onValidBarcode(String barcode) {
		if (itemStore.hasPrice(barcode)) {
			display.show(itemStore.getPrice(barcode));
		} else {
			display.show("Unknown barcode.");
		}
	}

	private void onInvalidBarcode() {
		display.show("Invalid barcode.");
	}
}
