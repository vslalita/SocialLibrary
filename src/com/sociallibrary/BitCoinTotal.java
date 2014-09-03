package com.sociallibrary;
public class BitCoinTotal extends PriceDecorator {

	int total;

	public BitCoinTotal(OrderPriceCalculator calculator) {
		super(calculator);
	}

	public int calculate(int bookTotal) {
		total = calculator.calculate(bookTotal);
		return addCharges();
	}

	private int addCharges() {
		return total + 2;
	}

}
