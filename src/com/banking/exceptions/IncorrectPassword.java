package com.banking.exceptions;

import com.banking.utility.Constants;

public class IncorrectPassword extends Exception{
	public IncorrectPassword() {
		System.out.println(Constants.INCORRECT_PASSWORD);
	}
}
