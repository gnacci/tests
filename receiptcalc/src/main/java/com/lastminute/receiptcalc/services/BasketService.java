package com.lastminute.receiptcalc.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lastminute.receiptcalc.model.BasketItem;

public interface BasketService {
	public List<BasketItem> buildBasketFromInputStream(InputStream is) throws IOException;
	
	public BasketItem buildBasketItemFromFullDescription (String fullDescription);
	
	public String basketItemToString (BasketItem basketItem);
}
