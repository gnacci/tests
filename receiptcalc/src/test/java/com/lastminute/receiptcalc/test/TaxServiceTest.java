package com.lastminute.receiptcalc.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lastminute.receiptcalc.model.TaxedItem;
import com.lastminute.receiptcalc.services.BasketService;
import com.lastminute.receiptcalc.services.TaxService;

@RunWith(WeldJUnit4Runner.class)
public class TaxServiceTest {

	@Inject
	private transient BasketService basketService;
	
	@Inject
	private transient TaxService taxService;
	
	@Test
	public void testTaxBasket() throws IOException {
		String exampleString = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		List<TaxedItem> l = taxService.buildTaxedBasket(basketService.buildBasketFromInputStream(is));
		Assert.assertEquals(3,l.size());
		Assert.assertEquals(new BigDecimal("0.00"),l.get(0).getTaxApplyed());
		Assert.assertEquals(new BigDecimal("1.50"),l.get(1).getTaxApplyed());
		Assert.assertEquals(new BigDecimal("0.00"), l.get(2).getTaxApplyed());
	}
}
