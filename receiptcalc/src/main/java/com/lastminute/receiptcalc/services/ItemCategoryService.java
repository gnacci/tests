package com.lastminute.receiptcalc.services;

import com.lastminute.receiptcalc.model.ItemCategory;

public interface ItemCategoryService {
	public ItemCategory discoverCategoryFromBasketDescription(String basketDescription);
}
