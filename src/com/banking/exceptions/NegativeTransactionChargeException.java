package com.banking.exceptions;

import com.banking.utility.Constants;

public class NegativeTransactionChargeException extends Exception {
	public NegativeTransactionChargeException() {
		System.out.println(Constants.NEGATIVE_TRANSACTION_CHARGE);
	}
}
