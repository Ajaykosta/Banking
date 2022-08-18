package com.banking.exceptions;

import com.banking.utility.Constants;

public class InsufficientBalanceException extends Exception{
	public InsufficientBalanceException() {
		System.out.println(Constants.INSUFFICIENT_BALANCE);
	}
}
