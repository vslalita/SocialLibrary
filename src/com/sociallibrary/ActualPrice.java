package com.sociallibrary;

public class ActualPrice implements OrderPriceCalculator {
	
	int bookTotal;
	@Override
	public int calculate(int bookTotal) {
		// TODO Auto-generated method stub
		this.bookTotal=bookTotal;
		return bookTotal;
	}

}
