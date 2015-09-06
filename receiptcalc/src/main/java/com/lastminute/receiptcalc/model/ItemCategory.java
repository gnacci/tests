package com.lastminute.receiptcalc.model;

public enum ItemCategory {
	BOOK(0),
	FOOD(0),
	MEDICAL_PRODUCT(0),
	OTHER(10);
	
	private final int taxToApply;
	
	ItemCategory(int taxToApply) {
		this.taxToApply = taxToApply;
	}
	
	public int taxToApply() {return this.taxToApply;}
}
