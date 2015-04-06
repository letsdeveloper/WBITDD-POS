package pos;

public class PointOfSale {

	private Display display;

	public PointOfSale(Display display) {
		this.display = display;
	}

	public void onBarcode(String barcode) {
		if (isValid(barcode)) {
			display.show("Unknown barcode.");
		} else {
			display.show("Invalid barcode.");
		}
	}

	private boolean isValid(String barcode) {
		return barcode != null && !barcode.isEmpty();
	}

}
