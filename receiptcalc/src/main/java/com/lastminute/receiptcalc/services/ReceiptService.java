package com.lastminute.receiptcalc.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.model.Receipt;
import com.lastminute.receiptcalc.model.TaxedItem;

public interface ReceiptService {	
	
	public Receipt buildReceiptFromTaxedBasket(List<TaxedItem> taxedItems);
	
	public void addTaxedItemToReceipt(Receipt receipt, TaxedItem taxedItem);
	
	public Receipt buildReceiptFromBasket (List<BasketItem> basketItems);
	
	public void addBasketItemToReceipt (Receipt receipt, BasketItem basketItem);
	
	public Receipt buildReceiptFromInputStream(InputStream is) throws IOException;
	
	public void addBasketItemToReceiptFromFullDescription (Receipt receipt, String fullDescription);
	
	public String receiptToString (Receipt receipt);
	
	public String receiptToHtml (Receipt receipt);
}
