package com.banking.exceptions;

import com.banking.utility.Constants;

public class UserNotFoundException extends Exception{
	public UserNotFoundException() {
		System.out.println(Constants.INVALID_USER);
	}
}
