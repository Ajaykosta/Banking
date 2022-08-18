package com.banking.exceptions;

import com.banking.utility.Constants;

public class ManagerRemoveException extends Exception{
	public ManagerRemoveException() {
		System.out.println(Constants.REMOVE_MANAGER);
	}
}
