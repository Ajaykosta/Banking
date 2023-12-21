package com.banking.exceptions;

import com.banking.utility.Constants;

public class NegativeAmountException extends Exception{
	public NegativeAmountException() {
		System.out.println(Constants.NEGATIVE_AMOUNT);
	}
}
