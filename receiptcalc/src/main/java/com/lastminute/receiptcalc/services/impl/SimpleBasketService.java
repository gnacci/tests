package com.lastminute.receiptcalc.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.services.BasketService;
import com.lastminute.receiptcalc.services.ItemCategoryService;

@Named
@Default
public class SimpleBasketService implements BasketService {
	private static final String importedMark = "imported";
	
	@Inject
	private transient ItemCategoryService itemCategoryService;
	
	public List<BasketItem> buildBasketFromInputStream(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		List<BasketItem> basketItemList = new ArrayList<BasketItem>();
		String line;
		while ((line = reader.readLine()) != null) {
			BasketItem basketItem = buildBasketItemFromFullDescription(line);
			if (basketItem != null) {
				basketItemList.add(basketItem);
			}
		}
		return basketItemList;
	}

	public BasketItem buildBasketItemFromFullDescription(String fullDescription) {
		if (fullDescription != null && fullDescription.trim().length() > 0) {
			String[] basketItemParts = fullDescription.trim().split(" ");
			if (basketItemParts.length > 3) {
				BasketItem basketItem = new BasketItem();
				basketItem.setQuantity(Integer.parseInt(basketItemParts[0]));
				basketItem.setBasePrice(new BigDecimal(basketItemParts[basketItemParts.length - 1]));
				if (fullDescription.contains(importedMark)) {
					basketItem.setImported(true);
				}
				basketItem.setDescription(rebuildBasketDescription(basketItemParts));
				basketItem.setCategory(itemCategoryService.discoverCategoryFromBasketDescription(basketItem.getDescription()));
				return basketItem;
			}
		}
		return null;
	}
	
	public String basketItemToString(BasketItem basketItem) {
		return basketItem.getQuantity() + " " + basketItem.getDescription() + " at " + basketItem.getBasePrice();
	}
	
	private String rebuildBasketDescription(String[] basketItemParts) {
		StringBuffer basketDescription = new StringBuffer();
		for (int i = 1; i < basketItemParts.length - 2; i++) {
			basketDescription.append(basketItemParts[i]);
			if (i != basketItemParts.length - 3) {
				basketDescription.append(" ");
			}
		}
		return basketDescription.toString();
	}	
}
