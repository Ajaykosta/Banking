package com.banking.exceptions;

import com.banking.utility.Constants;

public class SameAccountTransferException extends Exception{
	public SameAccountTransferException() {
		System.out.println(Constants.SAME_ACCOUNT_TRANSFER);
	}
}