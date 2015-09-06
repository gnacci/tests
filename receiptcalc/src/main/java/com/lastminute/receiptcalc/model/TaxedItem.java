package com.lastminute.receiptcalc.model;

import java.math.BigDecimal;

public class TaxedItem {
	private BasketItem basketItem;
	private BigDecimal taxApplyed;
	private BigDecimal finalPrice;
	
	public TaxedItem() {
	}

	public BasketItem getBasketItem() {
		return basketItem;
	}

	public void setBasketItem(BasketItem basketItem) {
		this.basketItem = basketItem;
	}

	public BigDecimal getTaxApplyed() {
		return taxApplyed;
	}

	public void setTaxApplyed(BigDecimal taxApplyed) {
		this.taxApplyed = taxApplyed;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
}
