package com.banking.exceptions;

import com.banking.utility.Constants;

public class UserNotActiveException extends Exception{
	public UserNotActiveException() {
		System.out.println(Constants.USER_NOT_ACTIVE);
	}
}
