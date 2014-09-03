package com.sociallibrary;

public class TaxTotal extends PriceDecorator {
	public TaxTotal(OrderPriceCalculator bookTotal){
		super(bookTotal);
	}
	
	int total;

	public int calculate(int bookTotal) {
		total = calculator.calculate(bookTotal);
		return addTax();
	}
	
	private int addTax() {
		return total + 1;
	}
}
