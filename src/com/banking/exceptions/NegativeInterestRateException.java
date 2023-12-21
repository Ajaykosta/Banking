package com.banking.exceptions;

import com.banking.utility.Constants;

public class NegativeInterestRateException extends Exception{
	public NegativeInterestRateException() {
		System.out.println(Constants.NEGATIVE_INTEREST_RATE);
	}
}
