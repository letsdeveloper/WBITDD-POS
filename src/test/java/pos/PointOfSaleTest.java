package pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PointOfSaleTest {

	@Test
	public void showsErrorMessageIfBarcodeIsNull() {
		TestDisplay testDisplay = new TestDisplay();
		PointOfSale pos = new PointOfSale(testDisplay);

		pos.onBarcode(null);

		assertThat(testDisplay.lastMessageShown, is("Invalid barcode."));
	}

	private static class TestDisplay implements Display {
		public String lastMessageShown;

		@Override
		public void show(String message) {
			lastMessageShown = message;
		}
	}
}
