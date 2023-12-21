package com.banking.exceptions;

import com.banking.utility.Constants;

public class UserExistException extends Exception {
	public UserExistException() {
		System.out.println(Constants.USER_EXIST);
	}
}
