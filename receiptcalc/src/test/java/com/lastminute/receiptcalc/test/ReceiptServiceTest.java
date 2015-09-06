package com.lastminute.receiptcalc.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lastminute.receiptcalc.model.Receipt;
import com.lastminute.receiptcalc.services.ReceiptService;

@RunWith(WeldJUnit4Runner.class)
public class ReceiptServiceTest {
	
	@Inject
	private transient ReceiptService receiptService;
	
	@Test
	public void testByAdd() {
		Receipt receipt = new Receipt();
		receiptService.addBasketItemToReceiptFromFullDescription(receipt, "1 book at 12.49");
		receiptService.addBasketItemToReceiptFromFullDescription(receipt, "1 music CD at 14.99");
		receiptService.addBasketItemToReceiptFromFullDescription(receipt, "1 chocolate bar at 0.85");
		Assert.assertEquals(new BigDecimal("1.50"),receipt.getTaxesTotal());
		Assert.assertEquals(new BigDecimal("29.83"),receipt.getReceiptTotal());
	}
	
	@Test
	public void testByIs() throws IOException {
		String exampleString = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		Receipt receipt = receiptService.buildReceiptFromInputStream(is);
		Assert.assertEquals(new BigDecimal("1.50"),receipt.getTaxesTotal());
		Assert.assertEquals(new BigDecimal("29.83"),receipt.getReceiptTotal());
	}
}
