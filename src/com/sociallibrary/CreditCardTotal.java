package com.sociallibrary;
public class CreditCardTotal extends PriceDecorator {
	
	public CreditCardTotal(OrderPriceCalculator bookTotal){
		super(bookTotal);
	}
	
	int total;

	public int calculate(int bookTotal) {
		total = calculator.calculate(bookTotal);
		return addCharges();
	}
	
	private int addCharges() {
		return total + 10;
	}

}
