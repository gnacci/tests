package com.lastminute.receiptcalc.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lastminute.receiptcalc.model.ItemCategory;
import com.lastminute.receiptcalc.services.ItemCategoryService;

@RunWith(WeldJUnit4Runner.class)
public class ItemCategoryServiceTest {
	
	@Inject
	private transient ItemCategoryService itemCategoryService;
	
	@Test
	public void testDiscoverCategoryFromBasketDescription() {
		Assert.assertEquals(ItemCategory.BOOK,itemCategoryService.discoverCategoryFromBasketDescription("book"));
		Assert.assertEquals(ItemCategory.OTHER,itemCategoryService.discoverCategoryFromBasketDescription("music CD"));
		Assert.assertEquals(ItemCategory.FOOD,itemCategoryService.discoverCategoryFromBasketDescription("chocolate bar"));
	}
}
