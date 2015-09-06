package com.lastminute.receiptcalc.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.model.Receipt;
import com.lastminute.receiptcalc.model.TaxedItem;
import com.lastminute.receiptcalc.services.BasketService;
import com.lastminute.receiptcalc.services.ReceiptService;
import com.lastminute.receiptcalc.services.TaxService;

public class SimpleReceiptService implements ReceiptService {	
	
	@Inject
	private transient TaxService taxService;
	
	@Inject
	private transient BasketService basketService;
	
	public Receipt buildReceiptFromTaxedBasket(List<TaxedItem> taxedItems) {
		Receipt receipt = new Receipt();
		for (TaxedItem taxedItem : taxedItems) {
			addTaxedItemToReceipt(receipt, taxedItem);
		}
		return receipt;
	}

	public void addTaxedItemToReceipt(Receipt receipt, TaxedItem taxedItem) {
		receipt.getTaxedItems().add(taxedItem);
		receipt.setTaxesTotal(receipt.getTaxesTotal().add(taxedItem.getTaxApplyed()));
		receipt.setReceiptTotal(receipt.getReceiptTotal().add(taxedItem.getFinalPrice()));
		
	}

	public Receipt buildReceiptFromBasket(List<BasketItem> basketItems) {
		return buildReceiptFromTaxedBasket(taxService.buildTaxedBasket(basketItems));
	}

	public void addBasketItemToReceipt(Receipt receipt, BasketItem basketItem) {
		addTaxedItemToReceipt(receipt, taxService.buildTaxedItem(basketItem));		
	}

	public Receipt buildReceiptFromInputStream(InputStream is) throws IOException {
		return buildReceiptFromBasket(basketService.buildBasketFromInputStream(is));
	}

	public void addBasketItemToReceiptFromFullDescription(Receipt receipt, String fullDescription) {
		addBasketItemToReceipt(receipt, basketService.buildBasketItemFromFullDescription(fullDescription));
	}

	public String receiptToString(Receipt receipt) {
		StringBuffer receiptBuffer = new StringBuffer();
		for (TaxedItem taxedItem : receipt.getTaxedItems()) {
			receiptBuffer.append(taxService.taxedItemToString(taxedItem)).append("\n");
		}
		receiptBuffer.append("Sales Taxes: ").append(receipt.getTaxesTotal()).append("\nTotal: " + receipt.getReceiptTotal() + "\n");
		return receiptBuffer.toString();
	}

	public String receiptToHtml(Receipt receipt) {
		StringBuffer receiptBuffer = new StringBuffer();
		for (TaxedItem taxedItem : receipt.getTaxedItems()) {
			receiptBuffer.append(taxService.taxedItemToString(taxedItem)).append("<br>");
		}
		receiptBuffer.append("Sales Taxes: ").append(receipt.getTaxesTotal()).append("<br>Total: " + receipt.getReceiptTotal() + "<br>");
		return receiptBuffer.toString();
	}

}
