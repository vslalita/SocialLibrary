package com.sociallibrary;
public abstract class PriceDecorator implements OrderPriceCalculator {

	public OrderPriceCalculator calculator;

	public PriceDecorator(OrderPriceCalculator calculator) {
		this.calculator = calculator;
	}

	public int calculate(int bookTotal) {
		return calculator.calculate(bookTotal);
	}
}
