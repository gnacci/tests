package com.lastminute.receiptcalc.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.services.BasketService;

@RunWith(WeldJUnit4Runner.class)
public class BasketServiceTest {

	@Inject
	private transient BasketService basketService;
	
	@Test
	public void testBuildBasketItemFromFullDescription() {
		Assert.assertEquals("1 book at 12.49",basketService.basketItemToString(basketService.buildBasketItemFromFullDescription("1 book at 12.49")));
		Assert.assertEquals("1 music CD at 14.99",basketService.basketItemToString(basketService.buildBasketItemFromFullDescription("1 music CD at 14.99")));
		Assert.assertEquals("1 chocolate bar at 0.85", basketService.basketItemToString(basketService.buildBasketItemFromFullDescription("1 chocolate bar at 0.85")));
	}
	
	@Test
	public void testBuildBasketFromInputStream() throws IOException {
		String exampleString = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
		InputStream is = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8));
		List<BasketItem> l = basketService.buildBasketFromInputStream(is);
		Assert.assertEquals(3,l.size());
		Assert.assertEquals("1 book at 12.49", basketService.basketItemToString(l.get(0)));
		Assert.assertEquals("1 music CD at 14.99",basketService.basketItemToString(l.get(1)));
		Assert.assertEquals("1 chocolate bar at 0.85", basketService.basketItemToString(l.get(2)));
	}
}
