package pos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PointOfSaleTest {

	private TestDisplay testDisplay;
	private TestItemStore testItemStore;
	private PointOfSale pos;

	@Before
	public void setUpPOS() {
		testDisplay = new TestDisplay();
		testItemStore = new TestItemStore();
		pos = new PointOfSale(testDisplay, testItemStore);
	}

	@Test
	public void showsErrorMessageIfBarcodeIsNull() {
		pos.onBarcode(null);

		assertThat(testDisplay.lastMessageShown, is("Invalid barcode."));
	}

	@Test
	public void showsErrorMessageIfBarcodeIsEmptyString() {
		pos.onBarcode("");

		assertThat(testDisplay.lastMessageShown, is("Invalid barcode."));
	}

	@Test
	public void showUnknownBarcodeIfNoRespectiveItemIsKown() {
		pos.onBarcode("123456789");

		assertThat(testDisplay.lastMessageShown, is("Unknown barcode."));
	}

	@Test
	public void showsPriceForItem() {
		String barcode = "123234345";
		testItemStore.registerItem(barcode, "$5.99");

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

		@Override
		public boolean hasPrice(String barcode) {
			return items.containsKey(barcode);
		}

	}
}
