package com.lastminute.receiptcalc.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.inject.Named;

import com.lastminute.receiptcalc.model.ItemCategory;
import com.lastminute.receiptcalc.services.ItemCategoryService;

@Named
@Default
public class SimpleItemCategoryService implements ItemCategoryService {

	private static Map<String,ItemCategory> categoryMatchingMap = new HashMap<String, ItemCategory>();
	
	static {
		categoryMatchingMap.put("book", ItemCategory.BOOK);
		categoryMatchingMap.put("chocolate", ItemCategory.FOOD);
		categoryMatchingMap.put("headache", ItemCategory.MEDICAL_PRODUCT);
	}
	
	public ItemCategory discoverCategoryFromBasketDescription(String basketDescription) {
		if (basketDescription != null && basketDescription.trim().length() > 0) {
			for (String matching : categoryMatchingMap.keySet()) {
				if (basketDescription.contains(matching)) {
					return categoryMatchingMap.get(matching);
				}
			}
		}		
		return ItemCategory.OTHER;
	}

}
