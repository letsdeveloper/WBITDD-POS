package pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

public class PointOfSaleTest {

	@Test
	public void showsErrorMessageIfBarcodeIsNull() {
		TestDisplay testDisplay = new TestDisplay();
		TestItemStore testItemStore = new TestItemStore();
		PointOfSale pos = new PointOfSale(testDisplay, testItemStore);

		pos.onBarcode(null);

		assertThat(testDisplay.lastMessageShown, is("Invalid barcode."));
	}

	@Test
	public void showsErrorMessageIfBarcodeIsEmptyString() {
		TestDisplay testDisplay = new TestDisplay();
		TestItemStore testItemStore = new TestItemStore();
		PointOfSale pos = new PointOfSale(testDisplay, testItemStore);

		pos.onBarcode("");

		assertThat(testDisplay.lastMessageShown, is("Invalid barcode."));
	}

	@Test
	public void showUnknownBarcodeIfNoRespectiveItemIsKown() {
		TestDisplay testDisplay = new TestDisplay();
		TestItemStore testItemStore = new TestItemStore();
		PointOfSale pos = new PointOfSale(testDisplay, testItemStore);

		pos.onBarcode("123456789");

		assertThat(testDisplay.lastMessageShown, is("Unknown barcode."));
	}

	@Test
	public void showsPriceForItem() {
		TestDisplay testDisplay = new TestDisplay();
		TestItemStore testItemStore = new TestItemStore();
		String barcode = "123234345";
		testItemStore.registerItem(barcode, "$5.99");
		PointOfSale pos = new PointOfSale(testDisplay, testItemStore);

		pos.onBarcode(barcode);

		assertThat(testDisplay.lastMessageShown, is("$5.99"));
	}

	private static class TestDisplay implements Display {
		public String lastMessageShown;

		@Override
		public void show(String message) {
			lastMessageShown = message;
		}
	}

	private static class TestItemStore implements ItemStore {
		private Map<String, String> items = new java.util.HashMap<String, String>();

		public void registerItem(String barcode, String price) {
			items.put(barcode, price);
		}

		@Override
		public String getPrice(String barcode) {
			return items.get(barcode);
		}

	}
}
