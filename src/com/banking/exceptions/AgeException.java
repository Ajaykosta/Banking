package com.banking.exceptions;

import com.banking.utility.Constants;

public class AgeException extends Exception {
	public AgeException() {
		System.out.println(Constants.AGE_RESTRICTION);
	}
}
