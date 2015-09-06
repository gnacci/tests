package com.lastminute.receiptcalc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private List<TaxedItem> taxedItems;
	private BigDecimal taxesTotal;
	private BigDecimal receiptTotal;
	
	public Receipt() {
		this.taxedItems = new ArrayList<TaxedItem>();
		this.taxesTotal = new BigDecimal(0);
		this.receiptTotal = new BigDecimal(0);
	}

	public void setTaxedItems(List<TaxedItem> taxedItems) {
		this.taxedItems = taxedItems;
	}

	public BigDecimal getTaxesTotal() {
		return taxesTotal;
	}
	
	public void setTaxesTotal(BigDecimal taxesTotal) {
		this.taxesTotal = taxesTotal;
	}

	public BigDecimal getReceiptTotal() {
		return receiptTotal;
	}
	
	public void setReceiptTotal(BigDecimal receiptTotal) {
		this.receiptTotal = receiptTotal;
	}

	public List<TaxedItem> getTaxedItems() {
		return taxedItems;
	}
	
}
