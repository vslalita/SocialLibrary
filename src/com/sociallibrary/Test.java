package com.sociallibrary;

public class Test {

	public static void main (String [] args){
		
		OrderPriceCalculator calctotal = new TaxTotal(new CreditCardTotal(new ActualPrice()));
		System.out.print("Total is" +calctotal.calculate(10));
		
	}
}
