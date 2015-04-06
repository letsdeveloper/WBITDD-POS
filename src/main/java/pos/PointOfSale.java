package pos;

public class PointOfSale {

	private Display display;

	public PointOfSale(Display display) {
		this.display = display;
	}

	public void onBarcode(Object object) {
		display.show("Invalid barcode.");
	}

}
