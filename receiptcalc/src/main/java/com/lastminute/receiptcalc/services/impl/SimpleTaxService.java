package com.lastminute.receiptcalc.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Named;

import com.lastminute.receiptcalc.model.BasketItem;
import com.lastminute.receiptcalc.model.TaxedItem;
import com.lastminute.receiptcalc.services.TaxService;

@Named
@Default
public class SimpleTaxService implements TaxService {
	private final int importedTaxesToApply = 5;
	
	public List<TaxedItem> buildTaxedBasket(List<BasketItem> basket) {
		List<TaxedItem> taxedItemList = new ArrayList<TaxedItem>();
		for (BasketItem basketItem : basket) {
			taxedItemList.add(buildTaxedItem(basketItem));
		}
		return taxedItemList;
	}
	
	public TaxedItem buildTaxedItem(BasketItem basketItem) {
		TaxedItem taxedItem = new TaxedItem();
		taxedItem.setBasketItem(basketItem);
		int taxesToApply = basketItem.getCategory().taxToApply() + (basketItem.isImported() ? importedTaxesToApply : 0);
		// rounding
		BigDecimal taxes = basketItem.getBasePrice().multiply(new BigDecimal(taxesToApply)).divide(new BigDecimal("100")).multiply(new BigDecimal("20")).setScale(0, RoundingMode.UP).divide(new BigDecimal("20"),2,RoundingMode.HALF_UP);
		taxedItem.setTaxApplyed(taxes);
		taxedItem.setFinalPrice(taxedItem.getTaxApplyed().add(taxedItem.getBasketItem().getBasePrice()));
		return taxedItem;
	}
	
	public String taxedItemToString(TaxedItem taxedItem) {
		return taxedItem.getBasketItem().getQuantity() + " " + taxedItem.getBasketItem().getDescription() + ": " + taxedItem.getFinalPrice();
	}

}
