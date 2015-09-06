package com.lastminute.receiptcalc.services;

import java.util.List;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.model.TaxedItem;

public interface TaxService {
	public List<TaxedItem> buildTaxedBasket(List<BasketItem> basket);
	
	public TaxedItem buildTaxedItem(BasketItem basketItem);
	
	public String taxedItemToString(TaxedItem taxedItem);
}
