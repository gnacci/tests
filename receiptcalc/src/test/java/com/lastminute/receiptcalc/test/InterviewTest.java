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
public class InterviewTest {
	
	@Inject
	private transient ReceiptService receiptService;
	
	@Test
	public void testInput1() throws IOException {
		String exampleString = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		Receipt receipt = receiptService.buildReceiptFromInputStream(is);
		// System.out.println(receiptService.receiptToString(receipt));
		Assert.assertEquals(new BigDecimal("1.50"),receipt.getTaxesTotal());
		Assert.assertEquals(new BigDecimal("29.83"),receipt.getReceiptTotal());		
	}
	
	@Test
	public void testInput2() throws IOException {
		String exampleString = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		Receipt receipt = receiptService.buildReceiptFromInputStream(is);
		// System.out.println(receiptService.receiptToString(receipt));
		Assert.assertEquals(new BigDecimal("7.65"),receipt.getTaxesTotal());
		Assert.assertEquals(new BigDecimal("65.15"),receipt.getReceiptTotal());
	}
	
	@Test
	public void testInput3() throws IOException {
		String exampleString = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		Receipt receipt = receiptService.buildReceiptFromInputStream(is);
		// System.out.println(receiptService.receiptToString(receipt));
		Assert.assertEquals(new BigDecimal("6.70"),receipt.getTaxesTotal());
		Assert.assertEquals(new BigDecimal("74.68"),receipt.getReceiptTotal());
	}
}
